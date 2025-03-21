package services;


import model.dao.PedidoDao;
import model.entities.Frutas;
import model.entities.Pedidos;

import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private final PedidoDao pedidoDao;
    private final Scanner sc;

    public PedidoService(PedidoDao pedidoDao, Scanner sc) {
        this.pedidoDao = pedidoDao;
        this.sc = sc;
    }

    public void listaPedidos() {
        List<Pedidos> list = pedidoDao.findAll();
        for (Pedidos p : list) {
            System.out.println(p);
        }
    }

    public void buscarPedidoPorId() {
        System.out.print("Digite o ID do pedido: ");
        int id = sc.nextInt();
        Pedidos pedidos = pedidoDao.findById(id);
        if (pedidos != null) {
            System.out.println(pedidos);
        } else {
            System.out.println("Pedido não encontrado!");
        }
    }

    public void inserirPedido(Frutas frutas) {
        System.out.print("Valor Total: ");
        double valorTotal = sc.nextDouble();
        System.out.print("Quantidade: ");
        sc.nextLine();
        Double quantidade = sc.nextDouble();

        Pedidos novoPedido = new Pedidos(null, frutas, valorTotal, quantidade);
        pedidoDao.insert(novoPedido);
        System.out.println("Pedido adicionada com sucesso! ID: " + novoPedido.getId());
    }

//    public void atualizarPedido() {
//        System.out.print("Digite o ID do pedido que deseja atualizar: ");
//        int id = sc.nextInt();
//        Pedidos pedidos = pedidoDao.findById(id);
//
//        if (pedidos == null) {
//            System.out.println("Pedido não encontrada!");
//            return;
//        }
//
//        System.out.print("Novo Fruta (" + pedidos.getFruta().getId() + "): ");
//        sc.nextLine();
//        Frutas fruta = sc.nextInt();
//        System.out.print("Novo valor total (" + pedidos.getValorTotal() + "): ");
//        double valorTotal = sc.nextDouble();
//        System.out.print("Nova quantidade (" + pedidos.getQuantidade() + "): ");
//        sc.nextLine();
//        double quantidade = sc.nextDouble();
//
//        pedidos.setFruta(fruta);
//        fruta.setPrecoUnitario(preco);
//        fruta.setUnidadeMedida(unidade);
//
//        frutasDao.update(fruta);
//        System.out.println("Fruta atualizada com sucesso!");
//    }

    public void deletarPedido() {
        System.out.print("Digite o ID do pedido a ser removido: ");
        int id = sc.nextInt();

        Pedidos pedidos = pedidoDao.findById(id);
        if (pedidos == null) {
            System.out.println("Pedido não encontrado!");
            return;
        }
        pedidoDao.deleteById(id);
        System.out.println("Pedido removido com sucesso!");
    }
}

