package br.com.veiculos.model;



public record TodosVeiculos(String codigo, String nome){

    @Override
    public String toString() {
        return  "(Código: " + codigo +
                ", Nome: " + nome +
                ")\n";
    }
}
