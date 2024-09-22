package DAO.impl;


import DAO.interfaces.DevisDAO;
import config.DatabaseConnection;
import models.Devis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DevisDAOImpl implements DevisDAO {
    private final Connection connection;

    public DevisDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void save(Devis devis) throws SQLException {
        String sql = "INSERT INTO Devis (id, dateEmission, dateValidee, accepte, projet_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, devis.getId());
            statement.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            statement.setDate(3, java.sql.Date.valueOf(devis.getDateValidee()));
            statement.setBoolean(4, devis.isAccepte());
            statement.setObject(5, devis.getProjetId());
            statement.executeUpdate();
        }
    }

    @Override
    public Devis getById(UUID id) throws SQLException {
        String sql = "SELECT * FROM Devis WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Devis(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getDate("dateEmission").toLocalDate(),
                        resultSet.getDate("dateValidee").toLocalDate(),
                        resultSet.getBoolean("accepte"),
                        (UUID) resultSet.getObject("projet_id")
                );
            }
        }
        return null;
    }
}