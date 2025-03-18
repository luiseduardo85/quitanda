package model.entities;

public class Frutas {

    private Integer id;
    private String name;
    private Double precoUnitario;
    private String unidadeMedida;

    public Frutas() {
    }

    public Frutas(Integer id, String name, Double precoUnitario, String unidadeMedida) {
        this.id = id;
        this.name = name;
        this.precoUnitario = precoUnitario;
        this.unidadeMedida = unidadeMedida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public String toString() {
        return "Frutas{" +
                "name='" + name + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", unidadeMedida='" + unidadeMedida + '\'' +
                '}';
    }
}
