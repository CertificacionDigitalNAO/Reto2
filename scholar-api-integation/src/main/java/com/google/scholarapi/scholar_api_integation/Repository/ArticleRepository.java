package com.google.scholarapi.scholar_api_integation.Repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.scholarapi.scholar_api_integation.Entity.Article;

/**
 * Repository interface for {@link Article} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * for {@link Article} entities.
 * </p>
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    /**
     * Finds an article by its unique article ID.
     *
     * @param articleId the unique ID of the article
     * @return an {@link Optional} containing the found {@link Article}, or empty if
     *         no article found
     */
    Optional<Article> findByArticleId(String articleId);

    /**
     * Finds all articles by the author's ID.
     *
     * @param authorId the unique ID of the author
     * @return a list of articles associated with the given author ID
     */
    List<Article> findByAuthorAuthorId(String authorId);
}