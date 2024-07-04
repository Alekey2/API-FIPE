package br.com.veiculos.principal;

import br.com.veiculos.model.DadosVeiculo;
import br.com.veiculos.model.Modelos;
import br.com.veiculos.model.TodosVeiculos;
import br.com.veiculos.service.ConsumoApi;
import br.com.veiculos.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner sc = new Scanner(System.in);
    private ConsumoApi ca = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";


    public void exibeMenu() {
        var menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                                
                Digite uma das opções para consultar
                """;

        System.out.println(menu);
        var opcao = sc.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        List<TodosVeiculos> todosVeiculos = new ArrayList<>();

        var json = ca.obterDados(endereco);
        var marcas = conversor.obterLista(json, TodosVeiculos.class);
        marcas.stream()
                .sorted(Comparator.comparing(TodosVeiculos::codigo))
                .forEach(System.out::println);


        System.out.println("Selecione uma opção pela código ! \nDigite o Código: ");
        var codigo = sc.nextLine();
        endereco = URL_BASE + "carros/marcas/" + codigo + "/modelos/";
        json = ca.obterDados(endereco);
        var modelos = conversor.obterDados(json, Modelos.class);
        modelos.modelos().stream()
                .sorted(Comparator.comparing(TodosVeiculos::codigo))
                .forEach(System.out::println);


        System.out.println("Digite o nome do carro para filtrar: ");
        var nomeVeiculo = sc.nextLine();

        List<TodosVeiculos> modelosFiltrados = modelos.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\nModelos Filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Agora escolha qual modelo você deseja ? \nDigite o Código: ");
        var cod = sc.nextLine();
        endereco = endereco + cod + "/anos";
        json = ca.obterDados(endereco);
        var versao = conversor.obterLista(json, TodosVeiculos.class);

        List<TodosVeiculos> anos = conversor.obterLista(json, TodosVeiculos.class);
        List<DadosVeiculo> veiculo = new ArrayList<>();
        for(int i=0; i<anos.size();i++){
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = ca.obterDados(enderecoAnos);
            DadosVeiculo veiculos = conversor.obterDados(json, DadosVeiculo.class);
            veiculo.add(veiculos);

        }
        System.out.println("Modelos encontrados: ");
        veiculo.forEach(System.out::println);


    }

}
