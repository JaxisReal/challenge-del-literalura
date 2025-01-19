package com.alura.literatura.controller;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/libros")
    public List<Libro> listarLibros(@RequestParam(required = false) String titulo) {
        if (titulo != null) {
            return libroService.buscarLibrosPorTitulo(titulo);
        }
        return libroService.listarTodosLosLibros();
    }

    @GetMapping("/libros/idioma")
    public List<Libro> listarLibrosPorIdioma(@RequestParam String idioma) {
        return libroService.listarLibrosPorIdioma(idioma);
    }

    @GetMapping("/autores/vivos")
    public List<Autor> listarAutoresVivosEnAnio(@RequestParam Integer anio) {
        return libroService.listarAutoresVivosEnAnio(anio);
    }

    @GetMapping("/libros/top")
    public List<Libro> obtenerTop10LibrosMasDescargados() {
        return libroService.obtenerTop10LibrosMasDescargados();
    }
}