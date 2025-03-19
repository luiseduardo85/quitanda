package org.example;

import model.dao.DaoFactory;
import model.dao.FrutasDao;
import model.entities.Frutas;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FrutasDao frutasDao = DaoFactory.createFrutasDao();

        Frutas fruta = frutasDao.findById(1);
        System.out.println(fruta);

        Frutas novaFruta = new Frutas(null, "Laranja", 1.35, "uni");

        frutasDao.insert(novaFruta);

        fruta.setName("Banana");
        fruta.setPrecoUnitario(1.38);
        fruta.setUnidadeMedida("uni");
        frutasDao.update(fruta);
//
//        System.out.println("Qual o id da fruta a ser removida ?");
//        int id = sc.nextInt();
//
//        frutasDao.deleteById(id);

        List<Frutas> list = frutasDao.findAll();
        for (Frutas f : list) {
            System.out.println(f);
        }
    }
}