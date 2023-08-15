package com.lojadediscos.repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/loja_virtual");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("dataBase666!");

        pooledDataSource.setMaxPoolSize(15);

        this.dataSource = pooledDataSource;
    }

    public Connection recuperarConexao() throws SQLException {
        return dataSource.getConnection();


    }


}
