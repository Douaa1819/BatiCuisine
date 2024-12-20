package DAO.impl;

import DAO.interfaces.MateriauxDAO;
import config.DatabaseConnection;
import enums.TypeComposant;
import models.Materiaux;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MateriauxDAOImpl implements MateriauxDAO {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public void create(Materiaux materiaux) {
        String query = "INSERT INTO Materiaux (id, nom, tva, typeComposant, projet_id, coutUnitaire, quantite, coutTransport, coefficientQualite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, materiaux.getId());
            stmt.setString(2, materiaux.getNom());
            stmt.setDouble(3, materiaux.getTva());
            stmt.setObject(4, materiaux.getTypeComposant().name(), java.sql.Types.OTHER);
            stmt.setObject(5, materiaux.getProjetId());
            stmt.setDouble(6, materiaux.getCoutUnitaire());
            stmt.setDouble(7, materiaux.getQuantite());
            stmt.setDouble(8, materiaux.getCoutTransport());
            stmt.setDouble(9, materiaux.getCoefficientQualite());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<Materiaux> getMateriauxByProjetId(UUID projetId) {
        List<Materiaux> materiauxList = new ArrayList<>();
        String sql = "SELECT * FROM materiaux WHERE projet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, projetId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Materiaux materiaux = new Materiaux(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("nom"),
                        resultSet.getDouble("tva"),
                        TypeComposant.valueOf(resultSet.getString("typeComposant")),
                        (UUID) resultSet.getObject("projet_id"),
                        resultSet.getDouble("coutunitaire"),
                        resultSet.getDouble("quantite"),
                        resultSet.getDouble("couttransport"),
                        resultSet.getDouble("coefficientQualite")
                );
                materiauxList.add(materiaux);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des matériaux : " + e.getMessage());
        }
        return materiauxList;
    }


  
}