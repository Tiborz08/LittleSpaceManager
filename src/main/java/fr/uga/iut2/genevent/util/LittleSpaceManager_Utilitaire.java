package fr.uga.iut2.genevent.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * La classe utilitaire du logiciel, elle apporte quelques fonctionnalités réutilisable ailleurs dans le code
 */
public class LittleSpaceManager_Utilitaire {

    private static AtomicInteger id = new AtomicInteger(0);

    /**
     * Génère un identifiant unique, incrémenté automatiquement
     * @return le nouvel identifiant à attribuer
     */
    public static int newId() {
        return id.incrementAndGet();
    }

    /**
     * Transforme une chaine de caractère et la mets en capital
     * @param str la chaine de caractère à modifier
     * @return la chaine de caractère modifiée (Exemple : oui --> Oui)
     */
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Remet tout les horaires à 0 sauf la date en elle meme
     * @param date la date à reset
     * @return la date à l'identique avec les horaires à 0
     */
    public static Date trim(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);

        return calendar.getTime();
    }


    /**
     * Supprime les accents d'une chaine de caractères
     * @param str la chaine de caractères
     * @return la chaine de caractères sans les accents (Exemple : été --> ete)
     */
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

    /**
     * Affiche une date sous forme numérique
     * @param date La date a afficher
     * @return La date sous forme numérique (Exemple : 16/04/2006)
     */
    public static String afficherDateChiffre(Date date) {
        String jour = String.valueOf(date.getDate());
        String mois = String.valueOf(date.getMonth() + 1);

        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        String annee = String.valueOf(date.getYear() + 1900);

        return jour + "/" + mois + "/" + annee;
    }
}
