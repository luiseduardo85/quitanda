package model.impl;

import Db.Db;
import Db.DbException;
import model.dao.PedidoDao;
import model.entities.Frutas;
import model.entities.Pedidos;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoDaoJDBC implements PedidoDao {

    private Connection conn;

    public PedidoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pedidos obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("INSERT INTO pedido "
                            +"(FrutaId, ValorTotal, Quantidade) "
                            +"VALUES "
                            +"(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getFruta().getId());
            st.setDouble(2, obj.getValorTotal());
            st.setDouble(3, obj.getQuantidade());

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
    public void update(Pedidos obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("UPDATE pedido "
                    +"SET FrutaId = ?, ValorTotal = ?, Quantidade = ? "
                    +"WHERE Id = ?");

            st.setInt(1, obj.getFruta().getId());
            st.setDouble(2, obj.getValorTotal());
            st.setDouble(3, obj.getQuantidade());
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
            st = conn.prepareStatement("DELETE FROM pedido WHERE Id= ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public Pedidos findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT * "
                    +"FROM pedido "
                    + "WHERE pedido.Id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Frutas frutas = instantiateFrutas(rs);
                Pedidos obj = instantiatePedido(rs,frutas);
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

    @Override
    public List<Pedidos> findByFrutaId(Frutas frutas) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT pedido.*, frutas.nome as FrutaNome "
                    + "From pedido INNER JOIN frutas "
                    + "ON pedido.frutaId = frutas.id "
                    + "WHERE pedido.id = ? "
                    + "ORDER BY NAME");

            st.setInt(1, frutas.getId());
            rs = st.executeQuery();

            List<Pedidos> list = new ArrayList<>();
            Map<Integer, Frutas> map = new HashMap<>();

            while (rs.next()){

                Frutas fruta = map.get(rs.getInt("FrutasId"));
                if(fruta == null){
                    fruta = instantiateFrutas(rs);
                    map.put(rs.getInt("DepartmentId"), fruta);
                }
                Pedidos obj = instantiatePedido(rs, fruta);
                list.add(obj);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }

    @Override
    public List<Pedidos> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT pedido.*, frutas.nome as FrutasNome "
                    + "From pedido INNER JOIN frutas "
                    + "ON pedido.FrutaId = frutas.id "
                    + "ORDER BY NOME");

            rs = st.executeQuery();

            List<Pedidos> list = new ArrayList<>();
            Map<Integer, Frutas> map = new HashMap<>();

            while (rs.next()){

                Frutas frutas = map.get(rs.getInt("Id"));
                if(frutas == null){
                    frutas = instantiateFrutas(rs);
                    map.put(rs.getInt("Id"), frutas);
                }
                Pedidos obj = instantiatePedido(rs, frutas);
                list.add(obj);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }

    private Pedidos instantiatePedido(ResultSet rs, Frutas frutas) throws SQLException{
        Pedidos obj = new Pedidos();
        obj.setId(rs.getInt("Id"));
        obj.setFruta(frutas);
        obj.setQuantidade(rs.getDouble("Quantidade"));
        obj.setValorTotal(rs.getDouble("ValorTotal"));
        return obj;
    }

    private Frutas instantiateFrutas(ResultSet rs) throws SQLException{
        Frutas obj = new Frutas();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Nome"));
        obj.setPrecoUnitario(rs.getDouble("PrecoUnitario"));
        obj.setUnidadeMedida(rs.getString("UnidadeMedida"));
        return obj;
    }
}
