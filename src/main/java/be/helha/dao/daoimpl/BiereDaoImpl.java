package be.helha.dao.daoimpl;


import be.helha.dao.BiereDao;
import be.helha.ebar.biere.Biere;
import be.helha.dao.daoimpl.DaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;




public class BiereDaoImpl implements BiereDao {
    @Override
    public Biere getBiere(String name) {
        String sql = "SELECT * FROM bieres WHERE nom = name ";
        Biere biere = null;
        DaoFactory dao = DaoFactory.getInstance();
        try (Connection connection = dao.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                biere = new Biere(
                        resultSet.getString("nom"),
                        resultSet.getString("type"),
                        resultSet.getString("couleur"),
                        resultSet.getString("brasserie")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return biere;
    }

    @Override
    public boolean supprimerBiere(String nom) {
        String sql = "DELETE FROM bieres WHERE nom = ?";
        DaoFactory dao = DaoFactory.getInstance();
        try (Connection connection = dao.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modifierBiere(Biere biere) {
        String sql = "UPDATE Bieres SET type = ?, couleur = ?, brasserie = ? WHERE nom = ?";
        DaoFactory dao = DaoFactory.getInstance();
        try (Connection connection = dao.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, biere.getType());
            preparedStatement.setString(2, biere.getCouleur());
            preparedStatement.setString(3, biere.getBrasserie());
            preparedStatement.setString(4, biere.getNom());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Biere> listerBieres() {
        String sql = "SELECT * FROM Bieres ORDER BY nom";
        List<Biere> bieres = new ArrayList<>();
        DaoFactory dao = DaoFactory.getInstance();
        try (Connection connection = dao.getConnexion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Biere biere = new Biere(
                        resultSet.getString("nom"),
                        resultSet.getString("type"),
                        resultSet.getString("couleur"),
                        resultSet.getString("brasserie")
                );
                bieres.add(biere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bieres;
    }




    public boolean ajouterBiere(Biere biere) throws SQLException {
        boolean success = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            DaoFactory dao = DaoFactory.getInstance();
            connection = dao.getConnexion();

            String query = "INSERT INTO bieres(nom, type, couleur, brasserie) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, biere.getNom());
            preparedStatement.setString(2, biere.getType());
            preparedStatement.setString(3, biere.getCouleur());
            preparedStatement.setString(4, biere.getBrasserie());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Une nouvelle bière a été insérée avec succès.");
                success = true;
            }
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return success;
    }
}



