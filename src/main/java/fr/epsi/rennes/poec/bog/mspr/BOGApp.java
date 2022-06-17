package fr.epsi.rennes.poec.bog.mspr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "fr.epsi.rennes.poec.bog.mspr")
public class BOGApp {

    public static void main(String[] args) throws SQLException {
        System.out.println("START BOGApp");
        SpringApplication.run(BOGApp.class, args);
    }
}
