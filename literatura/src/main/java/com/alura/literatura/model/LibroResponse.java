package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LibroResponse {
    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<AutorResponse> authors;

    @JsonProperty("download_count")
    private Integer downloadCount;

    @JsonProperty("languages")
    private List<String> languages;
}