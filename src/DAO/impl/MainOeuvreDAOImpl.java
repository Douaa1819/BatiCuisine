package DAO.impl;

import DAO.interfaces.MainOeuvreDAO;
import config.DatabaseConnection;
import enums.TypeComposant;
import models.MainOeuvre;
import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class MainOeuvreDAOImpl implements MainOeuvreDAO {

    private Connection connection;

    public MainOeuvreDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(MainOeuvre mainOeuvre) {
        String query = "INSERT INTO MainOeuvre (id, nom, tva, typeComposant, projet_id, tauxHoraire, heuresTravail, productiviteOuvrier) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, mainOeuvre.getId());
            statement.setString(2, mainOeuvre.getNom());
            statement.setDouble(3, mainOeuvre.getTva());
            statement.setObject(4, mainOeuvre.getTypeComposant(), java.sql.Types.OTHER);
            statement.setObject(5, mainOeuvre.getProjetId());
            statement.setDouble(6, mainOeuvre.getTauxHoraire());
            statement.setDouble(7, mainOeuvre.getHeuresTravail());
            statement.setDouble(8, mainOeuvre.getProductiviteOuvrier());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<MainOeuvre> findById(UUID id) {
        String query = "SELECT * FROM MainOeuvre WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MainOeuvre mainOeuvre = new MainOeuvre(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("nom"),
                        resultSet.getDouble("tauxTVA"),
                        TypeComposant.valueOf(resultSet.getString("typeComposant")),
                        (UUID) resultSet.getObject("projet_id"),
                        resultSet.getDouble("tauxHoraire"),
                        resultSet.getDouble("heuresTravail"),
                        resultSet.getDouble("productiviteOuvrier")
                );
                return Optional.of(mainOeuvre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

