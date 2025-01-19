package com.alura.literatura.service;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.AutorResponse;
import com.alura.literatura.model.Libro;
import com.alura.literatura.model.LibroResponse;
import com.alura.literatura.model.LibrosResponse;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LibroService libroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarLibrosDesdeAPI() {
        // Arrange
        String url = "https://gutendex.com/books/";
        LibrosResponse response = new LibrosResponse();
        List<LibroResponse> librosResponse = new ArrayList<>();
        LibroResponse libroResponse = new LibroResponse();
        libroResponse.setTitle("Test Book");
        libroResponse.setDownloadCount(100);  // Corregir la asignación a Integer
        List<String> idiomas = new ArrayList<>();
        idiomas.add("en");
        libroResponse.setLanguages(idiomas);
        List<AutorResponse> autores = new ArrayList<>();
        AutorResponse autorResponse = new AutorResponse();
        autorResponse.setName("Test Author");
        autorResponse.setBirthYear(1900);
        autorResponse.setDeathYear(1999);
        autores.add(autorResponse);
        libroResponse.setAuthors(autores);
        librosResponse.add(libroResponse);
        response.setResults(librosResponse);

        when(restTemplate.getForObject(url, LibrosResponse.class)).thenReturn(response);

        // Act
        libroService.guardarLibrosDesdeAPI();

        // Assert
        verify(autorRepository, times(1)).save(any(Autor.class));
        verify(libroRepository, times(1)).save(any(Libro.class));
    }
}