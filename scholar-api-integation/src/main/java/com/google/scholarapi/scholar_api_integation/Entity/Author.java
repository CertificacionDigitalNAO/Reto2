package com.google.scholarapi.scholar_api_integation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Representa una entidad de autor en la integración de la API de Scholar.
 * <p>
 * Esta clase mapea los detalles del autor obtenidos de la API y los almacena
 * en la tabla de base de datos asociada. Incluye metadatos como citaciones,
 * índice h e índice i10.
 * </p>
 */
@Data
@Entity
@Table(name = "author")
public class Author {

    /**
     * El identificador único del autor.
     */
    @Id
    @Column(name = "author_id", nullable = false, unique = true)
    private String authorId;

    /**
     * El nombre del autor.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Las afiliaciones del autor, que pueden incluir las instituciones u
     * organizaciones con las que el autor está asociado.
     */
    @Column(name = "affiliations", columnDefinition = "TEXT")
    private String affiliations;

    /**
     * El correo electrónico del autor.
     */
    @Column(name = "email")
    private String email;

    /**
     * La URL de la imagen en miniatura del autor.
     */
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    /**
     * El número total de citaciones del autor.
     */
    @Column(name = "citations_all")
    private Integer citationsAll;

    /**
     * El número de citaciones del autor desde 2016.
     */
    @Column(name = "citations_since_2016")
    private Integer citationsSince2016;

    /**
     * El índice h del autor para todos los tiempos.
     * <p>
     * El índice h es una métrica que mide tanto la productividad como el impacto
     * de citación de las publicaciones del autor.
     * </p>
     */
    @Column(name = "h_index_all")
    private Integer hIndexAll;

    /**
     * El índice h del autor desde 2016.
     */
    @Column(name = "h_index_since_2016")
    private Integer hIndexSince2016;

    /**
     * El índice i10 del autor para todos los tiempos.
     * <p>
     * El índice i10 es una métrica que representa el número de publicaciones con
     * al menos 10 citaciones.
     * </p>
     */
    @Column(name = "i10_index_all")
    private Integer i10IndexAll;

    /**
     * El índice i10 del autor desde 2016.
     */
    @Column(name = "i10_index_since_2016")
    private Integer i10IndexSince2016;

}