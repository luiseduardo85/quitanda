package model.impl;

import model.dao.FrutasDao;
import model.entities.Frutas;

import java.util.List;

public class FrutasDaoJDBC implements FrutasDao {
    @Override
    public void insert(Frutas obj) {

    }

    @Override
    public void update(Frutas obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Frutas findById(Integer id) {
        return null;
    }

    @Override
    public List<Frutas> findAll() {
        return List.of();
    }
}
