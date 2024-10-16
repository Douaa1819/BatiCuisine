package DAO.impl;

import DAO.interfaces.ClientDAO;
import config.DatabaseConnection;
import models.Client;

import java.sql.*;
import java.util.*;

public class ClientDAOImpl implements ClientDAO {
    private  Connection connection;

    public ClientDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }



@Override
    public Client createClient(Client client) {
        String sql = "INSERT INTO Client (id, nom, adresse, telephone, estProfessionnel) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setObject(1, client.getId());
            pstmt.setString(2, client.getNom());
            pstmt.setString(3, client.getAdress());
            pstmt.setString(4, client.getPhone());
            pstmt.setBoolean(5, client.isEstProfessionnel());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Client inséré dans la base de données avec succès.");
            } else {
                System.out.println("Aucun client n'a été inséré.");
            }

            return client;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du client", e);
        }
    }




    @Override
    public Optional<Client> getClient(UUID id) {
        String sql = "SELECT * FROM Client WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToClient(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du client", e);
        }
        return Optional.empty();
    }

    @Override
    public Map<UUID, Client> getAllClient() {
        String sql = "SELECT * FROM Client";
        Map<UUID, Client> clientsMap = new HashMap<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Parcourt les résultats et ajoute chaque client dans la HashMap
            while (rs.next()) {
                Client client = mapResultSetToClient(rs);
                clientsMap.put(client.getId(), client);  // Utilise l'ID du client comme clé
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des clients", e);
        }

        return clientsMap;
    }


    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String nom = rs.getString("nom");
        String adresse = rs.getString("adresse");
        String telephone = rs.getString("telephone");
        boolean estProfessionnel = rs.getBoolean("estProfessionnel");
        return new Client( id,nom, adresse, telephone, estProfessionnel);
    }


    @Override
    public List<Client> getClientsByName(String nomClient) throws SQLException {
        String sql = "SELECT id, nom, adresse, telephone, estProfessionnel FROM client WHERE nom = ?";
        List<Client> clients = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nomClient);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Client client = mapResultSetToClient(resultSet);
                    clients.add(client);
                }
            }
        }
        return clients;
    }


}