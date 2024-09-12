package com.google.scholarapi.scholar_api_integation.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.scholarapi.scholar_api_integation.Entity.Author;
import com.google.scholarapi.scholar_api_integation.Service.AuthorService;
import com.google.scholarapi.scholar_api_integation.Service.SerpApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private SerpApiService serpApiService; // Servicio para manejar las peticiones a SerpAPI

    /**
     * Está enfocado en obtener un autor desde la base de datos utilizando el
     * authorId. No hay ninguna interacción directa con la API externa de SerpApi
     * aquí, lo cual indica que este endpoint solo sirve para consultar autores ya
     * guardados en la base de datos.
     * 
     * @param authorId
     * @return
     */
    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable String authorId) {
        return authorService.getAuthorById(authorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Mi implementación está diseñada para obtener los datos de un autor desde
     * SerpApi y guardar esos datos en la base de datos. La funcionalidad está más
     * orientada a buscar datos en tiempo real desde una API externa y luego
     * persistirlos.
     * 
     * @param authorId
     * @return
     */
    @GetMapping("/fetch-author/{authorId}")
    public ResponseEntity<String> fetchAndSaveAuthor(@PathVariable String authorId) {
        try {
            serpApiService.fetchAndSaveAuthorData(authorId);
            return ResponseEntity.ok("Datos del autor y artículos guardados exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener o guardar los datos.");
        }
    }

    // Nuevo endpoint para obtener todos los autores
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

}