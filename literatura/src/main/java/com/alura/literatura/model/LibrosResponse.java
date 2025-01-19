package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LibrosResponse {
    @JsonProperty("results")
    private List<LibroResponse> results;
}