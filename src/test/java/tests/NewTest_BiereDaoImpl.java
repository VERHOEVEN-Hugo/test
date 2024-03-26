package tests;

import be.helha.dao.daoimpl.BiereDaoImpl;
import be.helha.ebar.biere.Biere;
import org.junit.jupiter.api.Test;
import be.helha.dao.daoimpl.DaoFactory;

import java.sql.*;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NewTest_BiereDaoImpl {

    @Test
    public void testGetBiere() {
        // Création d'une instance de DaoFactory
        DaoFactory daoFactory = DaoFactory.getInstance();

        // Création d'une instance de BiereDaoImpl avec l'instance de DaoFactory
        BiereDaoImpl biereDao = new BiereDaoImpl(daoFactory);

        // Nom de la bière à rechercher
        String nomBiere = "Pilsner"; // Remplacez "Pilsner" par le nom de la bière que vous souhaitez rechercher

        // Appel de la méthode getBiere pour récupérer la bière
        Biere biere = biereDao.getBiere(nomBiere);

        // Vérification que la bière récupérée n'est pas nulle
        assertNotNull(biere);

        // Affichage des informations de la bière récupérée (facultatif)
        System.out.println("Nom: " + biere.getNom() + ", Type: " + biere.getType() + ", Couleur: " + biere.getCouleur() + ", Brasserie: " + biere.getBrasserie());
    }

    @Test
    public void testSupprimerBiere() {
        // Nom de la bière à supprimer
        String nomBiere = "Pilsner"; // Remplacez "MaBiere" par le nom de la bière que vous souhaitez supprimer

        // Création d'une instance de DaoFactory
        DaoFactory daoFactory = DaoFactory.getInstance();

        // Création d'une instance de BiereDaoImpl avec l'instance de DaoFactory
        BiereDaoImpl biereDao = new BiereDaoImpl(daoFactory);

        // Suppression de la bière existante dans la base de données de test (ou de développement)
        boolean suppressionEffectuee = biereDao.supprimerBiere(nomBiere);

        // Vérification que la suppression a été effectuée avec succès
        assertTrue(suppressionEffectuee, "La bière " + nomBiere + " devrait etre supprimée de la base de données.");

        // Affichage d'un message de réussite
        System.out.println("Test de suppression de biere réussi pour la biere : " + nomBiere);
    }
    @Test
    public void testModifierBiere() {
        // Création d'une instance de DaoFactory
        DaoFactory daoFactory = DaoFactory.getInstance();

        // Récupération de l'instance de BiereDaoImpl à partir de DaoFactory
        BiereDaoImpl biereDao = daoFactory.getBiereDao();

        // Création d'une nouvelle bière pour la modification
        Biere biere = new Biere("IPA", "Brasserie B", "Ambrée", "Leger");

        // Modification de la bière dans la base de données
        boolean modificationEffectuee = biereDao.modifierBiere(biere);

        // Vérification que la modification a été effectuée avec succès
        assertTrue(modificationEffectuee, "La modification de la bière devrait être effectuée avec succès.");

        // Affichage d'un message de réussite
        System.out.println("Test de modification de bière réussi pour la bière : " + biere.getNom());
    }
    @Test
    public void testListerBieres() {
        // Création d'une instance de DaoFactory
        DaoFactory daoFactory = DaoFactory.getInstance();

        // Création d'une instance de BiereDaoImpl avec l'instance de DaoFactory
        BiereDaoImpl biereDao = new BiereDaoImpl(daoFactory);

        // Récupération de la liste des bières
        List<Biere> bieres = biereDao.listerBieres();

        // Vérification que la liste des bières n'est pas vide
        assertFalse(bieres.isEmpty(), "La liste des bières ne devrait pas être vide.");

        // Affichage des bières récupérées
        for (Biere biere : bieres) {
            System.out.println("Nom: " + biere.getNom() + ", Type: " + biere.getType() + ", Couleur: " + biere.getCouleur() + ", Brasserie: " + biere.getBrasserie());
        }
    }

    @Test
    public void testAjouterBiere() {
        // Création d'une instance de DaoFactory
        DaoFactory daoFactory = DaoFactory.getInstance();

        // Création d'une instance de BiereDaoImpl avec l'instance de DaoFactory
        BiereDaoImpl biereDao = new BiereDaoImpl(daoFactory);

        // Création d'une nouvelle bière à ajouter dans la base de données
        Biere biere = new Biere("NouvelleBiere", "TypeNouvelleBiere", "CouleurNouvelleBiere", "BrasserieNouvelleBiere");

        // Ajout de la nouvelle bière dans la base de données
        boolean ajoutEffectue = false;
        try {
            ajoutEffectue = biereDao.ajouterBiere(biere);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Vérification que l'ajout a été effectué avec succès
        assertTrue(ajoutEffectue, "L'ajout de la nouvelle bière devrait être effectué avec succès.");

        // Vérification que la bière ajoutée existe après l'ajout
        Biere biereAjoutee = biereDao.getBiere("NouvelleBiere");
        assertNotNull(biereAjoutee);
        assertEquals("TypeNouvelleBiere", biereAjoutee.getType());
        assertEquals("CouleurNouvelleBiere", biereAjoutee.getCouleur());
        assertEquals("BrasserieNouvelleBiere", biereAjoutee.getBrasserie());
    }


}
