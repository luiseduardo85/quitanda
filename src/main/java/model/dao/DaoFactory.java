package model.dao;

import Db.Db;
import model.impl.FrutasDaoJDBC;

public class DaoFactory {

    public static FrutasDao createFrutasDao(){

        return new FrutasDaoJDBC(Db.getConnection());
    }
}
