package com.google.scholarapi.scholar_api_integation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.scholarapi.scholar_api_integation.Entity.Article;
import com.google.scholarapi.scholar_api_integation.Repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> getArticlesByAuthorId(String authorId) {
        return articleRepository.findByAuthorAuthorId(authorId);
    }
}
