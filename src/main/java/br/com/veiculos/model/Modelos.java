package br.com.veiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos (List<TodosVeiculos> modelos){
    @Override
    public String toString() {
        return "(Modelos: " + modelos +
                ")";
    }
}
