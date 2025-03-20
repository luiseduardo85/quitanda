package services;

import model.dao.FrutasDao;
import model.entities.Frutas;

import java.util.List;
import java.util.Scanner;

public class FrutasService {
    private final FrutasDao frutasDao;
    private final Scanner sc;

    public FrutasService(FrutasDao frutasDao, Scanner sc) {
        this.frutasDao = frutasDao;
        this.sc = sc;
    }
    public void listarFrutas() {
        List<Frutas> list = frutasDao.findAll();
        for (Frutas f : list) {
            System.out.println(f);
        }
    }

    public void buscarFrutaPorId() {
        System.out.print("Digite o ID da fruta: ");
        int id = sc.nextInt();
        Frutas fruta = frutasDao.findById(id);
        if (fruta != null) {
            System.out.println(fruta);
        } else {
            System.out.println("Fruta não encontrada!");
        }
    }

        public void inserirFruta() {
            System.out.print("Nome da fruta: ");
            sc.nextLine();
            String nome = sc.nextLine();
            System.out.print("Preço unitário: ");
            double preco = sc.nextDouble();
            System.out.print("Unidade de medida: ");
            sc.nextLine();
            String unidade = sc.nextLine();

            Frutas novaFruta = new Frutas(null, nome, preco, unidade);
            frutasDao.insert(novaFruta);
            System.out.println("Fruta adicionada com sucesso! ID: " + novaFruta.getId());
        }

    public void atualizarFruta() {
        System.out.print("Digite o ID da fruta que deseja atualizar: ");
        int id = sc.nextInt();
        Frutas fruta = frutasDao.findById(id);

        if (fruta == null) {
            System.out.println("Fruta não encontrada!");
            return;
        }

        System.out.print("Novo nome (" + fruta.getName() + "): ");
        sc.nextLine();
        String nome = sc.nextLine();
        System.out.print("Novo preço (" + fruta.getPrecoUnitario() + "): ");
        double preco = sc.nextDouble();
        System.out.print("Nova unidade de medida (" + fruta.getUnidadeMedida() + "): ");
        sc.nextLine();
        String unidade = sc.nextLine();

        fruta.setName(nome);
        fruta.setPrecoUnitario(preco);
        fruta.setUnidadeMedida(unidade);

        frutasDao.update(fruta);
        System.out.println("Fruta atualizada com sucesso!");
    }

    public void deletarFruta() {
        System.out.print("Digite o ID da fruta a ser removida: ");
        int id = sc.nextInt();

        Frutas fruta = frutasDao.findById(id);
        if (fruta == null) {
            System.out.println("Fruta não encontrada!");
            return;
        }
        frutasDao.deleteById(id);
        System.out.println("Fruta removida com sucesso!");
    }}
