package be.helha.dao.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory() {
    }


    public static DaoFactory getInstance() {
        if (instance == null) instance = new DaoFactory();
        return instance;
    }


    public BiereDaoImpl getBiereDao() {
        return new BiereDaoImpl();
    }

    public static Connection getConnexion() throws SQLException {
        String URL = "jdbc:postgresql://localhost:5432/postgres";
        String USER = "postgre";
        String MDP = "1234";
        return DriverManager.getConnection(URL, USER, MDP);
    }
}

