package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByIdioma(String idioma);
    List<Libro> findTop10ByOrderByDescargasDesc();
}