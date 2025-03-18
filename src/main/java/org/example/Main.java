package org.example;

import model.dao.DaoFactory;
import model.dao.FrutasDao;

public class Main {
    public static void main(String[] args) {

        FrutasDao frutasDao = DaoFactory.createFrutasDao();
    }
}