package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "libros")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("download_count")
    private Integer descargas;

    @JsonAlias("languages")
    private String idioma;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}