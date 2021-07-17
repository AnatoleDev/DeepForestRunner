package com.factory.deepforestrunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DeepForestRunnerApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String... args) {
        SpringApplication.run(DeepForestRunnerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //Create the database table:
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS runners(" +
            "id INTEGER PRIMARY KEY," +
            " name VARCHAR(100) NOT NULL" +
            ")");
    }

}