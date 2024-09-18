package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private Connection connection;

    private static String url = "jdbc:postgresql://localhost:5432/jdbc";
    private static String user = "postgres";
    private static String password = "password";


    public DatabaseConnection() {
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
        // Accéder à l'instance unique de DatabaseConnection
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        // Récupérer la connexion
        Connection connection = databaseConnection.getConnection();

        if (connection != null) {
            System.out.println("Connexion à la base de données disponible !");
        }
    }
}
