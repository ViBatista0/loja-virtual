package com.lojadediscos.test;

import com.lojadediscos.repository.ConnectionFactory;

import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        try (Connection con = factory.recuperarConexao()) {

            con.setAutoCommit(false);

            try (PreparedStatement stm = con.prepareStatement("INSERT INTO PRODUTO (nome, marca) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ) {

                adicionarVariavel("RTX 4090", "Nvidia", stm);
                adicionarVariavel("Core I9", "Intel", stm);

                con.commit();


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rollback executado!");
                con.rollback();
            }
        }
    }

    public static void adicionarVariavel(String nome, String marca, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, marca);

        if (nome == "Core I9") {
            throw new RuntimeException("Não foi possível adicionar o produto!");
        }

        stm.execute();

        try (ResultSet resultSet = stm.getGeneratedKeys()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }
}
