package model.dao;

import model.entities.Frutas;

import java.util.List;

public interface FrutasDao {

    void insert(Frutas obj);
    void update(Frutas obj);
    void deleteById(Integer id);
    Frutas findById(Integer id);
    List<Frutas> findAll();

}
