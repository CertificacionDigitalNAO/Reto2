package com.google.scholarapi.scholar_api_integation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.scholarapi.scholar_api_integation.Entity.Article;
import com.google.scholarapi.scholar_api_integation.Repository.ArticleRepository;

/**
 * Servicio para manejar las operaciones relacionadas con los artículos.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Guarda un artículo en la base de datos.
     * 
     * @param article el objeto {@link Article} a guardar
     * @return el objeto {@link Article} guardado
     */
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    /**
     * Obtiene una lista de artículos por el ID del autor.
     * 
     * @param authorId el ID único del autor
     * @return una lista de artículos asociados con el ID del autor dado
     */
    public List<Article> getArticlesByAuthorId(String authorId) {
        return articleRepository.findByAuthorAuthorId(authorId);
    }
}