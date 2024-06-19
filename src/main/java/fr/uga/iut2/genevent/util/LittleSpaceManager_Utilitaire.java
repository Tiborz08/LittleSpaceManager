package fr.uga.iut2.genevent.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class LittleSpaceManager_Utilitaire {

    private static AtomicInteger id = new AtomicInteger(0);

    public static int newId() {
        return id.incrementAndGet();
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String removeAccents(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.replaceAll("[éèêë]", "e")
                .replaceAll("[ÉÈÊË]", "E")
                .replaceAll("[àâä]", "a")
                .replaceAll("[ÀÂÄ]", "A")
                .replaceAll("[îï]", "i")
                .replaceAll("[ÎÏ]", "I")
                .replaceAll("[ôö]", "o")
                .replaceAll("[ÔÖ]", "O")
                .replaceAll("[ûü]", "u")
                .replaceAll("[ÛÜ]", "U")
                .replaceAll("[ç]", "c")
                .replaceAll("[Ç]", "C");
    }

    public static String afficherDateChiffre(Date date) {
        String jour = String.valueOf(date.getDate());
        String mois = String.valueOf(date.getMonth() + 1);

        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        String annee = String.valueOf(date.getYear() + 1900);

        return jour + "/" + mois + "/" + annee;
    }

    public static String afficherDateLettres(Date date) {
        ArrayList<String> listeMois = new ArrayList<>(Arrays.asList("Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"));
        String mois = listeMois.get(date.getMonth());
        return date.getDate() + " " + mois + " " + (date.getYear() + 1900);
    }

}
