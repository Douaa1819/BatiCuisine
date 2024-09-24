package DAO.impl;


import DAO.interfaces.DevisDAO;
import config.DatabaseConnection;
import models.Devis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
    public Optional<Devis> getDevisByProjetId(UUID projetId) throws SQLException {
        String sql = "SELECT * FROM devis WHERE projet_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, projetId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Devis devis = new Devis(
                            UUID.fromString(rs.getString("id")),
                            rs.getDate("dateEmission").toLocalDate(),
                            rs.getDate("dateValidee").toLocalDate(),
                            rs.getBoolean("accepte"),
                            projetId
                    );
                    return Optional.of(devis);
                }
            }
        }
        return Optional.empty();
    }
}