package model.dao;

import model.impl.FrutasDaoJDBC;

public class DaoFactory {

    public static FrutasDao createFrutasDao(){
        return new FrutasDaoJDBC();
    }
}
