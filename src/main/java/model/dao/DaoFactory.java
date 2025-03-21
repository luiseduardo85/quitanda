package model.dao;

import Db.Db;
import model.impl.FrutasDaoJDBC;
import model.impl.PedidoDaoJDBC;

public class DaoFactory {

    public static FrutasDao createFrutasDao(){

        return new FrutasDaoJDBC(Db.getConnection());
    }

    public static PedidoDao createPedidosDao(){

        return new PedidoDaoJDBC(Db.getConnection());
    }
}
