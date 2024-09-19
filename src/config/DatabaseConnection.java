package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private Connection connection;

    private static String url = "jdbc:postgresql://localhost:5432/Bati_Cuisine";
    private static String user = "postgres";
    private static String password = "password";


    private DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            if (this.connection != null) {
                System.out.println("Connexion réussie !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }


    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }


    public  Connection getConnection() {
        return this.connection;
    }

    public static void run() {

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        Connection connection = databaseConnection.getConnection();

        if (connection != null) {
            System.out.println("Connexion à la base de données disponible !");
        }
    }
}
