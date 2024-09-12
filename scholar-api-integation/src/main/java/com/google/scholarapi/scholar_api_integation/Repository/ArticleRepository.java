package com.google.scholarapi.scholar_api_integation.Repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.scholarapi.scholar_api_integation.Entity.Article;

/**
 * Interfaz de repositorio para entidades {@link Article}.
 * <p>
 * Esta interfaz extiende {@link JpaRepository} para proporcionar operaciones
 * CRUD
 * para entidades {@link Article}.
 * </p>
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    /**
     * Encuentra un artículo por su ID único.
     *
     * @param articleId el ID único del artículo
     * @return un {@link Optional} que contiene el {@link Article} encontrado, o
     *         vacío si
     *         no se encuentra ningún artículo
     */
    Optional<Article> findByArticleId(String articleId);

    /**
     * Encuentra todos los artículos por el ID del autor.
     *
     * @param authorId el ID único del autor
     * @return una lista de artículos asociados con el ID del autor dado
     */
    List<Article> findByAuthorAuthorId(String authorId);
}