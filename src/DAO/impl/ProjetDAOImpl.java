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
import java.util.stream.Stream;

public class ProjetDAOImpl implements ProjetDAO {
    private final Connection connection;

    public ProjetDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void save(Projet projet) throws SQLException {
        String sql = "INSERT INTO projet (id, nomprojet, surface, margebeneficiaire, couttotal, etatprojet, tva, client_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, projet.getId());
            statement.setString(2, projet.getNomProjet());
            statement.setDouble(3, projet.getSurface());
            statement.setDouble(4, projet.getMargeBeneficiaire());
            statement.setDouble(5, projet.getCoutTotal());
            statement.setObject(6, projet.getEtatProjet(), java.sql.Types.OTHER);
            statement.setDouble(7, projet.getTva());
            statement.setObject(8, projet.getClientId());
            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Projet> getProjetById(UUID id) throws SQLException {
        String sql = "SELECT * FROM projet WHERE id = ?";
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
        String sql = "SELECT * FROM projet";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            return resultSetToStream(resultSet)
                    .map(rs -> {
                        try {
                            return new Projet(
                                    UUID.fromString(rs.getString("id")),
                                    rs.getString("nomprojet"),
                                    rs.getDouble("surface"),
                                    rs.getDouble("margebeneficiaire"),
                                    rs.getDouble("couttotal"),
                                    EtatProjet.valueOf(rs.getString("etatprojet")),
                                    rs.getDouble("tva"),
                                    UUID.fromString(rs.getString("client_id"))
                            );
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
        }
    }
    private Stream<ResultSet> resultSetToStream(ResultSet resultSet) throws SQLException {
        List<ResultSet> results = new ArrayList<>();
        while (resultSet.next()) {
            results.add(resultSet);
        }
        return results.stream();
    }


    @Override
    public void update(Projet projet) throws SQLException {
        Optional<Projet> projetOptional = getProjetById(projet.getId());
        projetOptional.ifPresent(existingProjet -> {
            String sql = "UPDATE projet SET nomprojet = ?, surface = ?, margebeneficiaire = ?, couttotal = ?, etatprojet = ?, tva = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, projet.getNomProjet());
                statement.setDouble(2, projet.getSurface());
                statement.setDouble(3, projet.getMargeBeneficiaire());
                statement.setDouble(4, projet.getCoutTotal());
                statement.setObject(5, projet.getEtatProjet().name(), java.sql.Types.OTHER);
                statement.setDouble(6, projet.getTva());
                statement.setObject(7, projet.getId());
                statement.executeUpdate();
                System.out.println("Projet mis à jour avec succès.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}