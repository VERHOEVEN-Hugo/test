package be.helha.dao.daoimpl;


import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

import javax.naming.ConfigurationException;

public class ParserConfig {

    public Persistance lireConfiguration(String filename) throws IOException, ConfigurationException {
        Path path = Paths.get(filename);
        Gson gson = new Gson();

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Persistance persistance = gson.fromJson(reader, Persistance.class);
            validatePersistance(persistance);
            return persistance;
        } catch (IOException e) {
            // Gérer l'erreur liée à la lecture du fichier
            throw new IOException("Erreur lors de la lecture du fichier de configuration.", e);
        }
    }

    private void validatePersistance(Persistance persistance) throws ConfigurationException {
        if (persistance.getType() == null || (!persistance.getType().equals("MOCK") && !persistance.getType().equals("DB"))) {
            throw new ConfigurationException("Type de système de persistance inconnu ou absent dans le fichier de configuration.");
        }

        if (persistance.getType().equals("DB")) {
            if (persistance.getUrl() == null || persistance.getUser() == null || persistance.getPassword() == null || persistance.getDao() == null) {
                throw new ConfigurationException("Les attributs obligatoires (url, user, password) sont absents pour le type de persistance DB.");
            }

        }
    }

}