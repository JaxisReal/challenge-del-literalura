package com.alura.literatura.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "autores")
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;
}