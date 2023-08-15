package com.lojadediscos.dao;

import com.lojadediscos.model.Categoria;
import com.lojadediscos.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {

        String sql = "INSERT INTO PRODUTO (nome, marca) VALUES (?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getMarca());

            stm.execute();

            try (ResultSet resultSet = stm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    produto.setId(resultSet.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {

        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT * FROM PRODUTO";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();


            try (ResultSet rst = statement.getResultSet();) {
                while (rst.next()) {
                    Produto produto =
                            new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                    produtos.add(produto);

                }

            }
        }

        return produtos;


    }

    public List<Produto> buscar(Categoria ct) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT * FROM PRODUTO WHERE CATEGORIA_ID = ?";


        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ct.getId());
            statement.execute();


            try (ResultSet rst = statement.getResultSet();) {
                while (rst.next()) {
                    Produto produto =
                            new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                    produtos.add(produto);

                }

            }
        }
        return produtos;
    }

}
