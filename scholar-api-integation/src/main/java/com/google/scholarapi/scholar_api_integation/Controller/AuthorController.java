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

/**
 * Controlador para manejar las solicitudes relacionadas con los autores.
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    /**
     * Servicio para manejar las operaciones relacionadas con los autores.
     */
    @Autowired
    private AuthorService authorService;

    /**
     * Servicio para manejar las peticiones a SerpAPI.
     */
    @Autowired
    private SerpApiService serpApiService;

    /**
     * Endpoint para obtener y guardar los datos de un autor.
     * 
     * @param authorId el ID único del autor
     * @return una {@link ResponseEntity} con el resultado de la operación
     */
    @GetMapping("/fetch-author/{authorId}")
    public ResponseEntity<?> fetchAndSaveAuthor(@PathVariable String authorId) {
        try {
            // Directamente retornar lo que el servicio ya maneja
            return serpApiService.fetchAndSaveAuthorData(authorId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener o guardar los datos: " + e.getMessage());
        }
    }

    /**
     * Endpoint para obtener todos los autores.
     * 
     * @return una {@link ResponseEntity} con la lista de todos los autores
     */
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

}