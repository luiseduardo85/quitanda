package services;


import model.dao.DaoFactory;
import model.dao.FrutasDao;
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

    public void inserirPedido() {
        System.out.print("Qual a fruta do pedido (ID): ");
        int id = sc.nextInt();
        sc.nextLine();

        FrutasDao frutasDao = DaoFactory.createFrutasDao();
        Frutas fruta = frutasDao.findById(id);

        if (fruta == null) {
            System.out.println("Fruta não encontrada com o ID informado.");
            return;
        }

        System.out.println("Vamos iniciar o cadastro do pedido abaixo:");

        System.out.print("Quantidade: ");
        Double quantidade = sc.nextDouble();
        sc.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida. Deve ser maior que zero.");
            return;
        }

        double valorTotal = quantidade * fruta.getPrecoUnitario();
        System.out.println("Valor Total: " + valorTotal);

        Pedidos novoPedido = new Pedidos(null, fruta, valorTotal, quantidade);
        pedidoDao.insert(novoPedido);

        System.out.println("Pedido adicionado com sucesso! ID: " + novoPedido.getId());
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

