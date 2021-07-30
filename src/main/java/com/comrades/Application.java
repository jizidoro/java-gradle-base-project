package com.comrades;


import com.comrades.repository.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
            injectStartData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SpringApplication.run(Application.class, args);
    }

    public static void injectStartData() throws SQLException {
        DataSource.getConnection();
    }
}


