package com.comrades.baseproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BaseProjectApplication {

    public static void main(String[] args) {
        try {
            fetchData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SpringApplication.run(BaseProjectApplication.class, args);
    }

    public static void fetchData() throws SQLException {
        final String SQL_QUERY = "select * from emp";
        List<Employee> employees = null;

        Connection con = DataSource.getConnection();
    }
}


