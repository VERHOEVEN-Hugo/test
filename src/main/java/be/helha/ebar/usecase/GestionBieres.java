package be.helha.ebar.usecase;

import be.helha.ebar.biere.Biere;

import java.sql.SQLException;
import java.util.List;

public interface GestionBieres {
    public boolean ajouterBiere(Biere biere) throws SQLException;
    public Biere getBiere (String nom );
    public boolean supprimerBiere (String nom);
    public boolean modifierBiere (Biere biere );
    public List<Biere> listerBieres();

}
