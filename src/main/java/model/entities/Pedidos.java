package model.entities;

public class Pedidos {

    private Integer id;
    private Frutas frutaId;
    private Double valorTotal;
    private Double quantidade;

    public Pedidos() {
    }

    public Pedidos(Integer id, Frutas frutaId, Double valorTotal, Double quantidade) {
        this.id = id;
        this.frutaId = frutaId;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Frutas getFruta() {
        return frutaId;
    }

    public void setFruta(Frutas frutaId) {
        this.frutaId = frutaId;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "frutaId=" + frutaId +
                ", valorTotal=" + valorTotal +
                ", quantidade=" + quantidade +
                '}';
    }
}
