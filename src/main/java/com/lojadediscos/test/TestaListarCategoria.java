package com.lojadediscos.test;

import com.lojadediscos.dao.CategoriaDAO;
import com.lojadediscos.model.Categoria;
import com.lojadediscos.model.Produto;
import com.lojadediscos.repository.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListarCategoria {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaCategoria = categoriaDAO.listarComProduto();
            listaCategoria.stream().forEach(lc -> {
                System.out.println(lc.getNome());
                for (Produto produto : lc.getProdutos()
                ) {
                    System.out.println("Categoria: " + lc.getNome());
                    System.out.println("Nome do produto: " + produto.getNome());
                    System.out.println();
                }
            });
//INNER JOIN
        }

    }
}
