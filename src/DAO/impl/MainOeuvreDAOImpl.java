package DAO.impl;

import DAO.interfaces.MainOeuvreDAO;
import config.DatabaseConnection;
import enums.TypeComposant;
import models.MainOeuvre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    public List<MainOeuvre> getMainOeuvreByProjetId(UUID projet_id) {
        List<MainOeuvre> mainOeuvreList = new ArrayList<>();
        String sql = "SELECT * FROM mainOeuvre WHERE projet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1,projet_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MainOeuvre mainOeuvre = new MainOeuvre(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("nom"),
                        resultSet.getDouble("tva"),
                        TypeComposant.valueOf(resultSet.getString("typeComposant")),
                        (UUID) resultSet.getObject("projet_id"),
                        resultSet.getDouble("tauxhoraire"),
                        resultSet.getDouble("heurestravail"),
                        resultSet.getDouble("productiviteouvrier")
                );
                mainOeuvreList.add(mainOeuvre);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la main-d'œuvre : " + e.getMessage());
        }
        return mainOeuvreList;
    }


}