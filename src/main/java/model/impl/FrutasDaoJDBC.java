package model.impl;

import Db.Db;
import Db.DbException;
import model.dao.FrutasDao;
import model.entities.Frutas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FrutasDaoJDBC implements FrutasDao {

    private Connection conn;

    public FrutasDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Frutas obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("INSERT INTO frutas "
                    +"(Nome, PrecoUnitario, UnidadeMedida) "
                    +"VALUES "
                    +"(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setDouble(2, obj.getPrecoUnitario());
            st.setString(3, obj.getUnidadeMedida());
            
            int rowAffected = st.executeUpdate();

            if(rowAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                Db.closeResultSet(rs);
            }else{
                throw new DbException("Insertion failed");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }

    }

    @Override
    public void update(Frutas obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("UPDATE frutas "
                            +"SET Nome = ?, PrecoUnitario = ?, UnidadeMedida = ? "
                            +"WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setDouble(2, obj.getPrecoUnitario());
            st.setString(3, obj.getUnidadeMedida());
            st.setInt(4, obj.getId());

            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("DELETE FROM frutas WHERE Id= ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public Frutas findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT * "
                    +"FROM frutas "
                    + "WHERE frutas.Id = ?"
                    );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Frutas obj = instantiateFrutas(rs);
                return obj;
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }

    }

    private Frutas instantiateFrutas(ResultSet rs) throws SQLException{
        Frutas obj = new Frutas();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Nome"));
        obj.setPrecoUnitario(rs.getDouble("PrecoUnitario"));
        obj.setUnidadeMedida(rs.getString("UnidadeMedida"));
        return obj;
    }

    @Override
    public List<Frutas> findAll() {
       PreparedStatement st = null;
       ResultSet rs = null;

       try{
           st = conn.prepareStatement("SELECT * FROM frutas");

           rs = st.executeQuery();

           List<Frutas> list = new ArrayList<>();

           while (rs.next()){
               Frutas obj = instantiateFrutas(rs);
               list.add(obj);
           }
           return list;
       }catch (SQLException e){
           throw new DbException(e.getMessage());
       }finally {
           Db.closeStatement(st);
           Db.closeResultSet(rs);
       }
    }
}
