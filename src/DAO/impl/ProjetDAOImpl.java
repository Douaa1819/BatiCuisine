package DAO.impl;

import DAO.interfaces.ProjetDAO;
import config.DatabaseConnection;
import enums.EtatProjet;
import models.Projet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetDAOImpl implements ProjetDAO {
    private final Connection connection;

    public ProjetDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void save(Projet projet) throws SQLException {
        String sql = "INSERT INTO projets (id, nomprojet, surface, margebeneficiaire, couttotal, etatprojet, tva, client_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, projet.getId());
            statement.setString(2, projet.getNomProjet());
            statement.setDouble(3, projet.getSurface());
            statement.setDouble(4, projet.getMargeBeneficiaire());
            statement.setDouble(5, projet.getCoutTotal());
            statement.setString(6, projet.getEtatProjet().name());
            statement.setDouble(7, projet.getTva());
            statement.setObject(8, projet.getClientId());
            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Projet> getProjetById(UUID id) throws SQLException {
        String sql = "SELECT * FROM projets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Projet(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("nomprojet"),
                        resultSet.getDouble("surface"),
                        resultSet.getDouble("margebeneficiaire"),
                        resultSet.getDouble("couttotal"),
                        EtatProjet.valueOf(resultSet.getString("etatprojet")),
                        resultSet.getDouble("tva"),
                        UUID.fromString(resultSet.getString("client_id"))
                ));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Projet> getAllProjets() throws SQLException {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM projets";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                projets.add(new Projet(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("nomprojet"),
                        resultSet.getDouble("surface"),
                        resultSet.getDouble("margebeneficiaire"),
                        resultSet.getDouble("couttotal"),
                        EtatProjet.valueOf(resultSet.getString("etatprojet")),
                        resultSet.getDouble("tva"),
                        UUID.fromString(resultSet.getString("client_id"))
                ));
            }
        }
        return projets;
    }
}
