package com.lojadediscos.test;

import com.lojadediscos.repository.ConnectionFactory;

import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUTO");
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("ID");
            System.out.println("ID: " + id);
            String nome = resultSet.getString("NOME");
            System.out.println("Nome: " + nome);
            String marca = resultSet.getString("MARCA");
            System.out.println("Marca: " + marca);
            System.out.println();
        }

        connection.close();


    }
}
