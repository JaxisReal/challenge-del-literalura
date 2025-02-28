package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqual(Integer anioNacimiento, Integer anioMuerte);
}