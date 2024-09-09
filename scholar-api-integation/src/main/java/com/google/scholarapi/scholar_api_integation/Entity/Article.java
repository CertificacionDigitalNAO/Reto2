package com.google.scholarapi.scholar_api_integation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents an article entity in the Scholar API integration.
 * <p>
 * Each article is associated with an author and includes metadata such as the
 * title, publication, and the number of citations.
 * </p>
 */
@Data
@Entity
@Table(name = "article")
public class Article {

    /**
     * The unique identifier for the article.
     */
    @Id
    @Column(name = "article_id", nullable = false, unique = true)
    private String articleId;

    /**
     * The author of the article.
     * <p>
     * This represents a many-to-one relationship with the Author entity, where multiple
     * articles can be associated with a single author. The relationship is loaded
     * lazily to improve performance when accessing article data without immediately
     * fetching the author details.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    /**
     * The title of the article.
     */
    @Column(nullable = false, length = 500)
    private String title;

    /**
     * The publication in which the article was published.
     */
    @Column(columnDefinition = "TEXT")
    private String publication;

    /**
     * The number of times the article has been cited in other works or publications.
     */
    @Column(name = "cited_by")
    private Integer citedBy;

    /**
     * The URL link to the article.
     * <p>
     * The length is set to 2083 characters, which is the maximum URL length supported
     * by most modern web browsers.
     * </p>
     */
    @Column(name = "link", length = 2083)
    private String link;
}
