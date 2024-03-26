package be.helha.dao.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        if (instance == null) instance = new DaoFactory();
        return instance;
    }

    public BiereDaoImpl getBiereDao() {
        return new BiereDaoImpl(this); // Passer l'instance actuelle de DaoFactory à BiereDaoImpl
    }

    public Connection getConnexion() throws SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connexion à la base de données MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebar?useSSL=false&serverTimezone=UTC", "root", "");

        }catch(SQLException | ClassNotFoundException e)
        {
            throw new RuntimeException();
        }
    return connection;
    }
}

