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
 * Representa una entidad de artículo en la integración de la API de Scholar.
 * <p>
 * Cada artículo está asociado con un autor e incluye metadatos como el título,
 * la publicación y el número de citaciones.
 * </p>
 */
@Data
@Entity
@Table(name = "article")
public class Article {

    /**
     * El identificador único del artículo.
     */
    @Id
    @Column(name = "article_id", nullable = false, unique = true)
    private String articleId;

    /**
     * El autor del artículo.
     * <p>
     * Esto representa una relación de muchos a uno con la entidad Author, donde
     * múltiples artículos pueden estar asociados con un solo autor. La relación se
     * carga de manera perezosa para mejorar el rendimiento al acceder a los datos
     * del artículo sin obtener inmediatamente los detalles del autor.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    /**
     * El título del artículo.
     */
    @Column(nullable = false, length = 500)
    private String title;

    /**
     * La publicación en la que se publicó el artículo.
     */
    @Column(columnDefinition = "TEXT")
    private String publication;

    /**
     * El número de veces que el artículo ha sido citado en otros trabajos o
     * publicaciones.
     */
    @Column(name = "cited_by")
    private Integer citedBy;

    /**
     * El enlace URL al artículo.
     * <p>
     * La longitud está establecida en 2083 caracteres, que es la longitud máxima
     * de URL soportada por la mayoría de los navegadores web modernos.
     * </p>
     */
    @Column(name = "link", length = 2083)
    private String link;
}