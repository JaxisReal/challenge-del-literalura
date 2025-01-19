package com.alura.literatura.service;

import com.alura.literatura.client.GutendexClient;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.model.LibroResponse;
import com.alura.literatura.model.LibrosResponse;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private GutendexClient gutendexClient;

    public void guardarLibrosDesdeAPI() {
        LibrosResponse response = gutendexClient.obtenerLibros();
        if (response != null && response.getResults() != null) {
            for (LibroResponse libroResponse : response.getResults()) {
                Autor autor = new Autor();
                autor.setNombre(libroResponse.getAuthors().get(0).getName());
                autor.setAnioNacimiento(libroResponse.getAuthors().get(0).getBirthYear());
                autor.setAnioMuerte(libroResponse.getAuthors().get(0).getDeathYear());

                Autor autorGuardado = autorRepository.save(autor);

                Libro libro = new Libro();
                libro.setTitulo(libroResponse.getTitle());
                libro.setDescargas(libroResponse.getDownloadCount());
                libro.setIdioma(libroResponse.getLanguages().get(0));
                libro.setAutor(autorGuardado);

                libroRepository.save(libro);
            }
        }
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> listarTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    public List<Autor> listarAutoresVivosEnAnio(Integer anio) {
        return autorRepository.findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqual(anio, anio);
    }

    public List<Libro> obtenerTop10LibrosMasDescargados() {
        return libroRepository.findTop10ByOrderByDescargasDesc();
    }
}