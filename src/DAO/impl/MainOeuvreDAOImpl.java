package DAO.impl;

import DAO.interfaces.MainOeuvreDAO;
import config.DatabaseConnection;
import enums.TypeComposant;
import models.MainOeuvre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
                        resultSet.getDouble("tauxHoraire"),
                        resultSet.getDouble("heuresTravaillees"),
                        resultSet.getDouble("productivite")
                );
                mainOeuvreList.add(mainOeuvre);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la main-d'œuvre : " + e.getMessage());
        }
        return mainOeuvreList;
    }

    @Override
    public double calculerCoutTotal(UUID projetId) {
        double total = 0.0;
        List<MainOeuvre> mainOeuvreList = getMainOeuvreByProjetId(projetId);
        for (MainOeuvre mainOeuvre : mainOeuvreList) {
            double coutMainOeuvre = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail() * mainOeuvre.getProductiviteOuvrier();
            total += coutMainOeuvre + (coutMainOeuvre * (mainOeuvre.getTva() / 100));
        }
        return total;
    }
    @Override
    public double calculerCoutTotalAvantTVA(UUID projetId) {
        double total = 0.0;
        String sql = "SELECT SUM(tauxhoraire * heurestravail) FROM mainoeuvre WHERE projet_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, projetId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}