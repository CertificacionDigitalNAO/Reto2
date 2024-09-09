package com.google.scholarapi.scholar_api_integation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "author_id", nullable = false, unique = true)
    private String authorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "affiliations", columnDefinition = "TEXT")
    private String affiliations;

    @Column(name = "email")
    private String email;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "citations_all")
    private Integer citationsAll;

    @Column(name = "citations_since_2016")
    private Integer citationsSince2016;

    @Column(name = "h_index_all")
    private Integer hIndexAll;

    @Column(name = "h_index_since_2016")
    private Integer hIndexSince2016;

    @Column(name = "i10_index_all")
    private Integer i10IndexAll;

    @Column(name = "i10_index_since_2016")
    private Integer i10IndexSince2016;

}
