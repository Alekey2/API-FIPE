package br.com.veiculos.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Marca") String marca,
                           @JsonAlias("Modelo") String modelo,
                           @JsonAlias("AnoModelo") Integer ano,
                           @JsonAlias("Combustivel")String combustivel,
                           @JsonAlias("Valor") String valor) {
    @Override
    public String toString() {
        return  "(Marca: " + marca +
                ", Modelo: " + modelo +
                ", Ano: " + ano +
                ", Combustivel: " + combustivel +
                ", Valor: R$ " + valor +
                ")";
    }
}
