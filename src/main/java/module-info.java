module genevent {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.beanutils;
    requires commons.digester;
    requires commons.logging;
    requires commons.collections;
    requires commons.validator;

    // Ouvre le package à javafx.fxml
    opens fr.uga.iut2.genevent.controleur to javafx.fxml;

    // Exporte d'autres packages si nécessaire
    exports fr.uga.iut2.genevent;
    exports fr.uga.iut2.genevent.controleur;
    exports fr.uga.iut2.genevent.vue;
}
