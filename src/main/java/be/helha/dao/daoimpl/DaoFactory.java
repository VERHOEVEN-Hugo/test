package be.helha.dao.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    private static DaoFactory instance;
    private static final String FICHIER_CONFIGURATION = "src/test/resources/configPostgres1.json";
    

    private DaoFactory() {
    }


    public static DaoFactory getInstance() {
        if (instance == null) instance = new DaoFactory();
        return instance;
    }


    public BiereDaoImpl getBiereDao() {
        return new BiereDaoImpl();
    }

    public Connection getConnexion() throws SQLException {
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        }catch(SQLException | ClassNotFoundException e)
        {
            throw new RuntimeException();
        }
return connection;
    }
}

