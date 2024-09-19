package DAO.impl;

import DAO.interfaces.ProjetDAO;
import config.DatabaseConnection;
import models.Projet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjetDAOImpl implements ProjetDAO {

    private Connection connection;

    public ProjetDAOImpl() {

        this.connection = DatabaseConnection.getInstance().getConnection();

    }

    @Override
    public void save(Projet projet) {
        String query = "INSERT INTO Projet (nomProjet, surface, margeBeneficiaire, coutTotal, etatProjet, tva, client_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int cont = 1;
            preparedStatement.setString(cont++, projet.getNomProjet());
            preparedStatement.setDouble(cont++, projet.getSurface());
            preparedStatement.setDouble(cont++, projet.getMargeBeneficiaire());
            preparedStatement.setDouble(cont++, projet.getCoutTotal());
            preparedStatement.setString(cont++, projet.getEtatProjet().name());
            preparedStatement.setDouble(cont++, projet.getTva());
            preparedStatement.setObject(cont++, projet.getClientId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Projet> findAll() {
        List<Projet> projets = new ArrayList<>();
        String query = "SELECT * FROM Projet";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                String nomProjet = resultSet.getString("nomProjet");
                double surface = resultSet.getDouble("surface");
                double margeBeneficiaire = resultSet.getDouble("margeBeneficiaire");
                double coutTotal = resultSet.getDouble("coutTotal");
                String etatProjet = resultSet.getString("etatProjet");
                double tva = resultSet.getDouble("tva");
                UUID clientId = (UUID) resultSet.getObject("client_id");
//                projets.add(new Projet(id, nomProjet, surface, margeBeneficiaire, coutTotal, etatProjet, tva, clientId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projets;
    }

//    @Override
//    public Projet findById(UUID id) {
//
//    }

}