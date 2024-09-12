package com.google.scholarapi.scholar_api_integation.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.scholarapi.scholar_api_integation.Entity.Author;

/**
 * Interfaz de repositorio para entidades {@link Author}.
 * <p>
 * Esta interfaz extiende {@link JpaRepository} para proporcionar operaciones
 * CRUD
 * para entidades {@link Author}.
 * </p>
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    /**
     * Encuentra un autor por su ID único.
     *
     * @param authorId el ID único del autor
     * @return un {@link Optional} que contiene el {@link Author} encontrado, o
     *         vacío si
     *         no se encuentra ningún autor
     */
    Optional<Author> findByAuthorId(String authorId);
}