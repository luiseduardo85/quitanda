package org.example;

import model.dao.DaoFactory;
import model.dao.FrutasDao;
import model.entities.Frutas;
import services.FrutasService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FrutasDao frutasDao = DaoFactory.createFrutasDao();
        FrutasService service = new FrutasService(frutasDao, sc);

        System.out.println("O que voce deseja fazer ?");

        int opcao;

        do {
            System.out.println("\n1 - Listar Frutas");
            System.out.println("2 - Buscar Fruta por ID");
            System.out.println("3 - Inserir nova Fruta");
            System.out.println("4 - Atualizar Fruta");
            System.out.println("5 - Deletar Fruta");
            System.out.println("6 - Sair");

            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    service.listarFrutas();
                    break;
                case 2:
                    service.buscarFrutaPorId();
                    break;
                case 3:
                    service.inserirFruta();
                    break;
                case 4:
                    service.atualizarFruta();
                    break;
                case 5:
                    service.deletarFruta();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}