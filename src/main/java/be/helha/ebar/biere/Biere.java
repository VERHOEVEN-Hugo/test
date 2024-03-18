package be.helha.ebar.biere;

import java.util.Objects;

public class Biere {

    private String nom;
    private  String couleur;
    private String type;
    private String brasserie;


    public Biere(String nom, String couleur, String type, String brasserie) {
        this.nom = nom;
        this.couleur = couleur;
        this.type = type;
        this.brasserie = brasserie;
    }

    public Biere(Biere b)
    {
        this.nom =nom;
        this.couleur = couleur;
        this.type = type;
        this.brasserie = brasserie;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrasserie() {
        return brasserie;
    }

    public void setBrasserie(String brasserie) {
        this.brasserie = brasserie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biere biere = (Biere) o;
        return Objects.equals(nom, biere.nom) && Objects.equals(couleur, biere.couleur) && Objects.equals(type, biere.type) && Objects.equals(brasserie, biere.brasserie);
    }


    @Override
    public String toString() {
        return "Biere{" +
                "nom='" + nom + '\'' +
                ", couleur='" + couleur + '\'' +
                ", type='" + type + '\'' +
                ", brasserie='" + brasserie + '\'' +
                '}';
    }
}
