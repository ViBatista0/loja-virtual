package com.lojadediscos.dao;

import com.lojadediscos.model.Categoria;
import com.lojadediscos.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {

        List<Categoria> categorias = new ArrayList<Categoria>();

        String sql = "SELECT ID, NOME_CATEGORIA FROM CATEGORIA";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.execute();

            try (ResultSet rst = stm.getResultSet()) {
                while (rst.next()) {
                    Categoria categoria =
                            new Categoria(rst.getInt(1), rst.getString(2));

                    categorias.add(categoria);
                }
            }
        }

        return categorias;
    }

    public List<Categoria> listarComProduto() throws SQLException {
        Categoria ultima = null;
        List<Categoria> categorias = new ArrayList<Categoria>();

        String sql = "SELECT C.ID, C.NOME_CATEGORIA, P.ID, P.NOME, P.MARCA FROM CATEGORIA C INNER JOIN "
                + " PRODUTO P ON C.ID = P.CATEGORIA_ID";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.execute();

            try (ResultSet rst = stm.getResultSet()) {
                while (rst.next()) {

                    if (ultima == null || !(ultima.getNome()).equals(rst.getString(2))) {

                        Categoria categoria =
                                new Categoria(rst.getInt(1), rst.getString(2));
                        ultima = categoria;

                        categorias.add(categoria);
                    }

                    Produto produto = new Produto(rst.getInt(3), rst.getString(4),
                            rst.getString(5));
                    ultima.adicionar(produto);

                }
            }
        }

        return categorias;
    }
}
