package tests;




import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import be.helha.dao.daoimpl.ParserConfig;
import be.helha.dao.daoimpl.Persistance;
import org.junit.jupiter.api.Test;

import javax.naming.ConfigurationException;

public class Test_Parser {

    @Test
    public void testParser() throws IOException, ConfigurationException {
        ParserConfig parser = new ParserConfig();
        Persistance persistance = parser.lireConfiguration("src/test/resources/configPostgres1.json");

        assertEquals("DB", persistance.getType());
        assertEquals("be.helha.daoimpl.BiereDaoImpl", persistance.getDao());
        assertEquals("jdbc:postgresql://localhost:5432/postgres", persistance.getUrl());
        assertEquals("postgres", persistance.getUser());
        assertEquals("1234", persistance.getPassword());
    }
    @Test
    public void testParser2() throws IOException, ConfigurationException {
        ParserConfig parser = new ParserConfig();
        Persistance persistance = parser.lireConfiguration("src/test/resources/configPostgres1.json");

        assertEquals("DB", persistance.getType());
        assertEquals("be.helha.daoimpl.BiereDaoImpl", persistance.getDao());
        assertEquals("jdbc:postgresql://localhost:5432/postgres", persistance.getUrl());
        assertEquals("postgres", persistance.getUser());
        assertEquals("12345", persistance.getPassword());
    }

}

