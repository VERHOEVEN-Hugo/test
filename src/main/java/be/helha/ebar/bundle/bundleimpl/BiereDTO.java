package be.helha.ebar.bundle.bundleimpl;



import be.helha.ebar.bundle.Bundle;

import java.util.HashMap;
import java.util.Map;

public class BiereDTO implements Bundle {

    public static final String MESSAGE = "message";
    public static final String BIERE = "biere";
    public static final String OPERATION_REUSSIE = "operationReussie";
    public static final String LISTE = "liste";
    public static final String NOM = "nom";

    private Map<String, Object> map = new HashMap<String, Object>();


    public BiereDTO() {

        this.map.put(BiereDTO.OPERATION_REUSSIE, true);
        this.map.put(BiereDTO.MESSAGE, "");
    }
    public void put(String clef, Object valeur) {
        this.map.put(clef, valeur);
    }

    public Object get(String clef) {
        return this.map.get(clef);
    }

    public void vider() {
        this.map.clear();
    }
}



