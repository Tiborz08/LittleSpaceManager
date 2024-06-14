package fr.uga.iut2.genevent.util;

import java.util.concurrent.atomic.AtomicInteger;

public class LittleSpaceManager_Utilitaire {

    private AtomicInteger id = new AtomicInteger(0);

    public int newId() {
        return id.incrementAndGet();
    }

}
