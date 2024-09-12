package com.google.scholarapi.scholar_api_integation.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.scholarapi.scholar_api_integation.Entity.Author;
import com.google.scholarapi.scholar_api_integation.Repository.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthorById(String authorId) {
        return authorRepository.findById(authorId);
    }

    // Método para obtener todos los autores
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
