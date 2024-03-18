package be.helha.ebar.bundle;

import java.util.HashMap;
import java.util.Map;


public interface Bundle {
    /*
    public static final String MESSAGE = "message";
    public static final String BIERE = "biere";
    public static final String OPERATION_REUSSIE = "operationReussie";
    public static final String LISTE = "liste";
    public static final String NOM = "nom";
    */
    Map<String, Object> map = new HashMap<String, Object>();
    void put(String clef, Object valeur);
    Object get(String clef);
    void vider();
}
