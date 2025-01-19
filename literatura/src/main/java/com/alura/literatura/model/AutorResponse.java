package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AutorResponse {
    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private Integer birthYear;

    @JsonProperty("death_year")
    private Integer deathYear;
}