package com.google.scholarapi.scholar_api_integation.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.scholarapi.scholar_api_integation.Entity.Author;
import com.google.scholarapi.scholar_api_integation.Repository.AuthorRepository;

import java.util.Optional;

/**
 * Servicio para manejar las operaciones relacionadas con los autores.
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Guarda un autor en la base de datos.
     * 
     * @param author el objeto {@link Author} a guardar
     * @return el objeto {@link Author} guardado
     */
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    /**
     * Obtiene un autor por su ID.
     * 
     * @param authorId el ID único del autor
     * @return un {@link Optional} que contiene el autor si se encuentra
     */
    public Optional<Author> getAuthorById(String authorId) {
        return authorRepository.findById(authorId);
    }

    /**
     * Obtiene todos los autores.
     * 
     * @return una lista de todos los autores
     */
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}