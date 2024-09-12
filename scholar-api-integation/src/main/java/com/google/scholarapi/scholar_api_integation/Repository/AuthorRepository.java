package com.google.scholarapi.scholar_api_integation.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.scholarapi.scholar_api_integation.Entity.Author;

/**
 * Repository interface for {@link Author} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * for {@link Author} entities.
 * </p>
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    /**
     * Finds an author by their unique author ID.
     *
     * @param authorId the unique ID of the author
     * @return an {@link Optional} containing the found {@link Author}, or empty if no author found
     */
    Optional<Author> findByAuthorId(String authorId);
}