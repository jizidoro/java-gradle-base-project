package com.comrades;


import com.comrades.repository.DataSource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Documentation APIs v1.0"))
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


