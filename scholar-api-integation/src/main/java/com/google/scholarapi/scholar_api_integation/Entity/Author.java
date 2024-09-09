package com.google.scholarapi.scholar_api_integation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents an author entity in the Scholar API integration.
 * <p>
 * This class maps the author's details obtained from the API and stores them
 * in the associated database table. It includes metadata like citations,
 * h-index, and i10-index.
 * </p>
 */
@Data
@Entity
@Table(name = "author")
public class Author {

    /**
     * The unique identifier for the author.
     */
    @Id
    @Column(name = "author_id", nullable = false, unique = true)
    private String authorId;

    /**
     * The name of the author.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The affiliations of the author, which may include the institutions or
     * organizations the author is associated with.
     */
    @Column(name = "affiliations", columnDefinition = "TEXT")
    private String affiliations;

    /**
     * The email of the author.
     */
    @Column(name = "email")
    private String email;

    /**
     * The URL of the author's thumbnail image.
     */
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    /**
     * The total number of citations for the author.
     */
    @Column(name = "citations_all")
    private Integer citationsAll;

    /**
     * The number of citations for the author since 2016.
     */
    @Column(name = "citations_since_2016")
    private Integer citationsSince2016;

    /**
     * The h-index of the author for all time.
     * <p>
     * The h-index is a metric that measures both the productivity and citation
     * impact of the author's publications.
     * </p>
     */
    @Column(name = "h_index_all")
    private Integer hIndexAll;

    /**
     * The h-index of the author since 2016.
     */
    @Column(name = "h_index_since_2016")
    private Integer hIndexSince2016;

    /**
     * The i10-index of the author for all time.
     * <p>
     * The i10-index is a metric that represents the number of publications with
     * at least 10 citations.
     * </p>
     */
    @Column(name = "i10_index_all")
    private Integer i10IndexAll;

    /**
     * The i10-index of the author since 2016.
     */
    @Column(name = "i10_index_since_2016")
    private Integer i10IndexSince2016;

}
