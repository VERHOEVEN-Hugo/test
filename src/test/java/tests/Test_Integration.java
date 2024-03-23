package tests;

/*
 * Tests d'intégration de type JUnit
 * Chaque cas d'utilisation est testé individuellement, les uns aprés les autres
 */


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import be.helha.ebar.biere.Biere;
import be.helha.ebar.bundle.bundleimpl.BiereDTO;
import be.helha.ebar.usecase.GestionBieres;
import be.helha.ebar.usecase.usecaseimpl.GestionBieresimpl;

@TestMethodOrder(OrderAnnotation.class)
public class Test_Integration {

    private static List<Biere> bieres;
    private static GestionBieres gestionBieres;
    private static BiereDTO bieredto;

    @BeforeAll
    // sera exécuté une fois avant toutes les méthodes
    static void initialisation() {
        gestionBieres = new GestionBieresimpl();
        bieredto = new BiereDTO();
        bieres = new ArrayList<Biere>(6);
        // ajout des bières (liste déjà triée)
        bieres.add(new Biere("Blanche De Bruxelles", "Blanche", "blanche",
                "Brasserie Lefébvre"));
        bieres.add(new Biere("Blanche de Hoegaarden", "Blanche", "blanche",
                "Brasserie De Kluis"));
        bieres.add(new Biere("Chimay Bleue", "Trappiste", "brune",
                "Abbaye de Scourmont"));
        bieres.add(new Biere("Chimay Rouge", "Trappiste", "brune",
                "Abbaye de Scourmont"));
        bieres.add(new Biere("Floreffe Blonde", "Abbaye", "blonde",
                "Brasserie Lefébvre"));
        bieres.add(new Biere("Floreffe Triple", "Abbaye", "blonde",
                "Brasserie Lefébvre"));
    }

    @Test
    @Order(1)
    public void testAjouter() {
        for (Biere b : bieres) {
            bieredto.put(BiereDTO.BIERE, b);
            gestionBieres.ajouterBiere(bieredtoo);
            assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
        }
        // tentative d'ajout d'une biere déjà présente
        bieredto.put(BiereDTO.BIERE, bieres.get(0));
        gestionBieres.ajouterBiere(bieredto);
        assertFalse((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));

        // tentative d'ajout d'une biere dont il manque des données
        bieredto.put(BiereDTO.BIERE, new Biere(null,null,null,null));
        gestionBieres.ajouterBiere(bieredto);
        assertFalse((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));

        bieredto.put(BiereDTO.BIERE, new Biere("nom",null,null,null));
        gestionBieres.ajouterBiere(bieredto);
        assertFalse((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));

        bieredto.put(BiereDTO.BIERE, new Biere("nom","type",null,null));
        gestionBieres.ajouterBiere(bieredto);
        assertFalse((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));

        bieredto.put(BiereDTO.BIERE, new Biere("nom","type","couleur",null));
        gestionBieres.ajouterBiere(bieredto);
        assertFalse((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
    }

    @Test
    @Order(2)
    public void testLister() {
        gestionBieres.lister(bieredto);
        @SuppressWarnings("unchecked")
        List<Biere> bieresObtenues = (List<Biere>) bieredto.get(BiereDTO.LISTE);
        assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
        for (int i = 0; i < bieres.size(); i++) {
            assertEquals(bieresObtenues.get(i), bieres.get(i));
        }
    }

    @Test
    @Order(3)
    public void testRechercher() {
        for (Biere b : bieres) {
            bieredto.put(BiereDTO.NOM, b.getNom());
            gestionBieres.rechercherBiere(bieredto);
            Biere biereObtenue = (Biere) bieredto.get(BiereDTO.BIERE);
            assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
            assertEquals(biereObtenue, b);
        }
    }

    @Test
    @Order(4)
    public void testModifier() {
        // modification biere existante
        bieredto.put(BiereDTO.NOM, bieres.get(0).getNom());
        gestionBieres.rechercherBiere(bieredto);
        assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
        Biere biere = (Biere) bieredto.get(BiereDTO.BIERE);
        biere.setBrasserie(biere.getBrasserie() + "!");
        biere.setType(biere.getType() + "!");
        biere.setCouleur(biere.getCouleur() + "!");
        gestionBieres.modifierBiere(bieredto);
        assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
        bieredto.vider();
        bieredto.put(BiereDTO.NOM, biere.getNom());
        gestionBieres.rechercherBiere(bieredto);
        assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
        Biere biereObtenue = (Biere) bieredto.get(BiereDTO.BIERE);
        assertEquals(biereObtenue, biere);

        //tentative de modification d'une biere inexistante
        bieredto.put(BiereDTO.BIERE, new Biere("?", "?", "?", "?"));
        gestionBieres.modifierBiere(bieredto);
        assertFalse((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
    }

    @Test
    @Order(5)
    public void testSupprimer() {
        for (Biere b : bieres) {
            bieredto.put(BiereDTO.NOM, b.getNom());
            gestionBieres.supprimerBiere(bieredto);
            assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
        }
        gestionBieres.lister(bieredto);
        @SuppressWarnings("unchecked")
        List<Biere> bieresObtenues = (List<Biere>) bieredto.get(BiereDTO.LISTE);
        assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
        assertEquals(0, bieresObtenues.size());

        //tentative de suppression d'une biere inexistante
        bieredto.put(BiereDTO.BIERE, new Biere("?", "?", "?", "?"));
        gestionBieres.supprimerBiere(bieredto);
        assertFalse((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));

    }

    @Test
    @Order(6)
    public void testListeTriee() {
        Biere biereU = new Biere("u", "u", "u", "u");
        Biere biereZ = new Biere("z", "z", "z", "z");
        Biere biereA = new Biere("a", "a", "a", "a");
        bieredto.put(BiereDTO.BIERE, biereU);
        gestionBieres.ajouterBiere(bieredto);
        bieredto.put(BiereDTO.BIERE, biereZ);
        gestionBieres.ajouterBiere(bieredto);
        bieredto.put(BiereDTO.BIERE, biereA);
        gestionBieres.ajouterBiere(bieredto);
        gestionBieres.lister(bieredto);
        assertTrue((boolean) bieredto.get(BiereDTO.OPERATION_REUSSIE));
        @SuppressWarnings("unchecked")
        List<Biere> bieresObtenues = (List<Biere>) bieredto.get(BiereDTO.LISTE);
        assertEquals( bieresObtenues.get(0), biereA);
        assertEquals( bieresObtenues.get(1), biereU);
        assertEquals( bieresObtenues.get(2), biereZ);
    }

}
