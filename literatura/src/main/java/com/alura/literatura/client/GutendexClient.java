package com.alura.literatura.client;

import com.alura.literatura.model.LibrosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GutendexClient {

    @Autowired
    private RestTemplate restTemplate;

    public LibrosResponse obtenerLibros() {
        String url = "https://gutendex.com/books/";
        return restTemplate.getForObject(url, LibrosResponse.class);
    }
}