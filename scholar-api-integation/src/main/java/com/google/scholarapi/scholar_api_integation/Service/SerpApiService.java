package com.google.scholarapi.scholar_api_integation.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.scholarapi.scholar_api_integation.Entity.Article;
import com.google.scholarapi.scholar_api_integation.Entity.Author;
import com.google.scholarapi.scholar_api_integation.Repository.ArticleRepository;
import com.google.scholarapi.scholar_api_integation.Repository.AuthorRepository;

/**
 * Servicio para manejar las operaciones relacionadas con SerpAPI.
 */
@Service
public class SerpApiService {

    /**
     * Repositorio para manejar las operaciones relacionadas con los autores.
     */
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Repositorio para manejar las operaciones relacionadas con los artículos.
     */
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Entorno de Spring para acceder a las variables de entorno.
     */
    @Autowired
    private Environment env;

    /**
     * URL del endpoint de SerpAPI.
     */
    private final String SERPAPI_ENDPOINT = "https://serpapi.com/search.json";

    /**
     * Obtiene y guarda los datos de un autor desde SerpAPI.
     * 
     * @param authorId el ID único del autor
     * @return una {@link ResponseEntity} con el resultado de la operación
     * @throws Exception si ocurre un error durante la obtención o el guardado de
     *                   los datos
     */
    public ResponseEntity<?> fetchAndSaveAuthorData(String authorId) throws Exception {

        // Crear una instancia de RestTemplate para realizar peticiones HTTP
        RestTemplate restTemplate = new RestTemplate();

        // Crear una instancia de ObjectMapper para parsear la respuesta JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Obtener la clave de API de las variables de entorno
        String apiKey = env.getProperty("SERPAPI_KEY");

        // Construir la URL para la petición a SerpAPI
        String url = SERPAPI_ENDPOINT + "?engine=google_scholar_author&author_id=" + authorId + "&api_key=" + apiKey;

        // Realizar la petición GET a la URL construida
        String response = restTemplate.getForObject(url, String.class);

        // Parsear la respuesta JSON
        JsonNode rootNode = objectMapper.readTree(response);

        // Mostrar el JSON completo en la consola para depuración
        System.out.println("Respuesta JSON completa: " + response);

        /**
         * Verifica si el autor ya existe en la base de datos.
         * 
         * @param authorId el ID único del autor
         * @return una {@link ResponseEntity} con un mensaje de conflicto si el autor ya
         *         existe
         */
        Optional<Author> existingAuthor = authorRepository.findById(authorId);
        if (existingAuthor.isPresent()) {
            // Si el autor ya existe, retornar un mensaje de conflicto
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El autor con ID " + authorId + " ya existe en la base de datos.");
        }

        /**
         * Parsea y guarda los datos del autor obtenidos de la respuesta JSON.
         * 
         * @param authorJson el nodo JSON que contiene los datos del autor
         * @param authorId   el ID único del autor
         * @return el objeto {@link Author} con los datos parseados
         */
        JsonNode authorJson = rootNode.path("author");

        Author author = new Author();
        author.setAuthorId(authorId);
        author.setName(authorJson.path("name").asText());
        author.setAffiliations(authorJson.has("affiliations") ? authorJson.path("affiliations").asText() : null);
        author.setEmail(authorJson.has("email") ? authorJson.path("email").asText() : null);
        author.setThumbnailUrl(authorJson.has("thumbnail") ? authorJson.path("thumbnail").asText() : null);

        // Parsear los datos de citaciones
        JsonNode citedBy = rootNode.path("cited_by");
        if (citedBy.has("table")) {
            for (JsonNode obj : citedBy.path("table")) {
                if (obj.has("citations")) {
                    JsonNode citations = obj.path("citations");
                    author.setCitationsAll(citations.path("all").asInt());
                    author.setCitationsSince2016(citations.path("since_2016").asInt());
                }
                if (obj.has("h_index")) {
                    JsonNode hIndex = obj.path("h_index");
                    author.setHIndexAll(hIndex.path("all").asInt());
                    author.setHIndexSince2016(hIndex.path("since_2016").asInt());
                }
                if (obj.has("i10_index")) {
                    JsonNode i10Index = obj.path("i10_index");
                    author.setI10IndexAll(i10Index.path("all").asInt());
                    author.setI10IndexSince2016(i10Index.path("since_2016").asInt());
                }
            }
        }

        // Guardar el autor en la base de datos
        authorRepository.save(author);

        // ---------------------------------
        // PARSEAR Y GUARDAR LOS ARTÍCULOS DEL AUTOR
        // ---------------------------------
        if (rootNode.has("articles")) {
            for (JsonNode articleJson : rootNode.path("articles")) {
                String articleId = articleJson.path("citation_id").asText();

                // Verificar si el artículo ya existe
                Optional<Article> existingArticle = articleRepository.findById(articleId);
                if (existingArticle.isPresent()) {
                    System.out.println("El artículo con ID " + articleId + " ya existe.");
                    continue;
                }

                // Si el artículo no existe, crear un nuevo registro
                Article article = new Article();
                article.setArticleId(articleId);
                article.setAuthor(author);
                article.setTitle(articleJson.path("title").asText());
                article.setPublication(articleJson.path("publication").asText());
                article.setCitedBy(
                        articleJson.has("cited_by") ? articleJson.path("cited_by").path("value").asInt() : 0);
                article.setLink(articleJson.has("link") ? articleJson.path("link").asText() : null);

                // Guardar el artículo en la base de datos
                articleRepository.save(article);
            }
        }

        // Retornar el JSON de respuesta para mostrarlo en Postman o navegador
        return ResponseEntity.status(HttpStatus.OK).body(rootNode);
    }

}