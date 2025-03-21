package model.dao;


import model.entities.Frutas;
import model.entities.Pedidos;

import java.util.List;

public interface PedidoDao {

    void insert(Pedidos obj);
    void update(Pedidos obj);
    void deleteById(Integer id);
    Pedidos findById(Integer id);
    List<Pedidos> findAll();
    List<Pedidos> findByFrutaId(Frutas frutas);
}
