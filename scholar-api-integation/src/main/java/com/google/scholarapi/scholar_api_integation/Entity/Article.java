package com.google.scholarapi.scholar_api_integation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "article_id", nullable = false, unique = true)
    private String articleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String publication;

    @Column(name = "cited_by")
    private Integer citedBy;

    @Column(name = "link", length = 2083)
    private String link;
}
