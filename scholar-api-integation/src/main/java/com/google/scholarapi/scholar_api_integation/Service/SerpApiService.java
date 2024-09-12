package com.google.scholarapi.scholar_api_integation.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.scholarapi.scholar_api_integation.Entity.Article;
import com.google.scholarapi.scholar_api_integation.Entity.Author;
import com.google.scholarapi.scholar_api_integation.Repository.ArticleRepository;
import com.google.scholarapi.scholar_api_integation.Repository.AuthorRepository;

@Service
public class SerpApiService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private Environment env; // Para acceder a las variables de entorno

    private final String SERPAPI_ENDPOINT = "https://serpapi.com/search.json";

    /*
     * 1. Realizar una solicitud a la API de SerpApi para obtener información sobre
     * un autor y sus artículos.
     * 2. Extraer y procesar los datos recibidos en formato JSON.
     * 3. Guardar los datos del autor y sus artículos en la base de datos.
     */

    public void fetchAndSaveAuthorData(String authorId) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        String apiKey = env.getProperty("SERPAPI_KEY");
        String url = SERPAPI_ENDPOINT + "?engine=google_scholar_author&author_id=" + authorId + "&api_key=" + apiKey;

        String response = restTemplate.getForObject(url, String.class);

        JsonNode rootNode = objectMapper.readTree(response);

        // ---------------------------------
        // PARSEAR Y GUARDAR LOS DATOS DEL AUTOR
        // ---------------------------------
        JsonNode authorJson = rootNode.path("author");

        // Verificar si el autor ya existe
        Optional<Author> existingAuthor = authorRepository.findById(authorId);
        if (existingAuthor.isPresent()) {
            System.out.println("El autor ya existe en la base de datos.");
            return; // Salir si el autor ya existe
        }

        // Si el autor no existe, crear un nuevo registro
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

        // Guardar los datos del autor en la base de datos
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
                    continue; // Saltar si el artículo ya existe
                }

                // Si el artículo no existe, crear un nuevo registro
                Article article = new Article();
                article.setArticleId(articleId);
                article.setAuthor(author); // Relacionar este artículo con el autor guardado
                article.setTitle(articleJson.path("title").asText());
                article.setPublication(articleJson.path("publication").asText());
                article.setCitedBy(
                        articleJson.has("cited_by") ? articleJson.path("cited_by").path("value").asInt() : 0);
                article.setLink(articleJson.has("link") ? articleJson.path("link").asText() : null);

                // Guardar el artículo en la base de datos
                articleRepository.save(article);
            }
        }
    }
}
