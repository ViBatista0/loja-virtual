package com.lojadediscos.test;

import com.lojadediscos.dao.ProdutoDAO;
import com.lojadediscos.model.Produto;
import com.lojadediscos.repository.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class TestaProdutoDAO {

    public static void main(String[] args) throws SQLException {


        Produto jogoUm = new Produto("Mortal Kombat", "Warner");

        try (Connection con = new ConnectionFactory().recuperarConexao()) {
            ProdutoDAO produto = new ProdutoDAO(con);
//            produto.salvar(jogoUm);
            List<Produto> listaProdutos = produto.listar();
            listaProdutos.forEach(lp -> System.out.println(lp));
        }

        System.out.println(jogoUm);

    }
}
