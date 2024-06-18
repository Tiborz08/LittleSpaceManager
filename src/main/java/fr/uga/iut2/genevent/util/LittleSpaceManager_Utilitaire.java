package fr.uga.iut2.genevent.util;

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

}
