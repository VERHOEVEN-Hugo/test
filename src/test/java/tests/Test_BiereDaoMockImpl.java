package tests;
/*
 *  Test unitaire de la classe BiereDaoMockImpl
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import be.helha.dao.BiereDao;
import be.helha.dao.daoimpl.BiereDaoImpl;
import be.helha.ebar.biere.Biere;

@TestMethodOrder(OrderAnnotation.class)
public class Test_BiereDaoMockImpl {
    private static List<Biere> bieres;
    protected static BiereDao biereDao;

    @BeforeAll
    // sera exécuté une fois avant toutes les méthodes
    static void initialiserLeDao() {
        biereDao = new BiereDaoImpl();
    }

    @BeforeAll
    // sera exécuté avant toutes les méthodes
    static void initialiserListeBieres() {
        bieres = new ArrayList<Biere>(6);
        // ajout des bière (liste déjà triée)
        bieres.add(new Biere("Blanche De Bruxelles", "Blanche", "blanche", "Brasserie Lefèvre"));
        bieres.add(new Biere("Blanche de Hoegaarden", "Blanche", "blanche", "Brasserie De Kluis"));
        bieres.add(new Biere("Chimay Bleue", "Trappiste", "brune", "Abbaye de Scourmont"));
        bieres.add(new Biere("Chimay Rouge", "Trappiste", "brune", "Abbaye de Scourmont"));
        bieres.add(new Biere("Floreffe Blonde", "Abbaye", "blonde", "Brasserie Lefèvre"));
        bieres.add(new Biere("Floreffe Triple", "Abbaye", "blonde", "Brasserie Lefèvre"));
    }



    @Test
    @Order(2)
    public void testLister() {
        List<Biere> bieresObtenues = biereDao.listerBieres();
        for (int i = 0; i < bieres.size(); i++) {
            assertEquals(bieresObtenues.get(i), bieres.get(i));
        }
    }

    @Test
    @Order(3)
    public void testRechercher() {
        for (Biere b : bieres) {
            Biere biereObtenue = biereDao.getBiere(b.getNom());
            assertEquals(biereObtenue, b);
        }
    }

    @Test
    @Order(4)
    public void testModifier() {
        Biere biere = biereDao.getBiere(bieres.get(0).getNom());
        biere.setBrasserie(biere.getBrasserie() + "!");
        biere.setType(biere.getType() + "!");
        biere.setCouleur(biere.getCouleur() + "!");
        assertTrue(biereDao.modifierBiere(biere));
        Biere biereObtenue = biereDao.getBiere(biere.getNom());
        assertEquals(biereObtenue, biere);
    }

    @Test
    @Order(5)
    public void testSupprimer() {
        for (Biere b : bieres) {
            assertTrue(biereDao.supprimerBiere(b.getNom()));
        }
        List<Biere> bieresObtenues = biereDao.listerBieres();
        assertNotNull(bieresObtenues);
        assertEquals(0, bieresObtenues.size());
    }

    @Test
    @Order(6)
    public void testListeTriee() throws SQLException {
        Biere biereU = new Biere("u", "u", "u", "u");
        Biere biereZ = new Biere("z", "z", "z", "z");
        Biere biereA = new Biere("a", "a", "a", "a");
        biereDao.ajouterBiere(biereZ);
        biereDao.ajouterBiere(biereA);
        biereDao.ajouterBiere(biereU);
        List<Biere> bieresObtenues = biereDao.listerBieres();
        assertEquals(bieresObtenues.get(0), biereA);
        assertEquals(bieresObtenues.get(1), biereU);
        assertEquals(bieresObtenues.get(2), biereZ);
    }

}
