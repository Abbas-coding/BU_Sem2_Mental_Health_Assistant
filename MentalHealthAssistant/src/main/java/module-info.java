module source.mentalhealthassistant {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    // Add these:
    requires com.google.gson;   // Gson module
    requires java.net.http;
    requires kotlin.stdlib;     // HTTP Client module

    opens source.mentalhealthassistant to javafx.fxml;
    opens source.mentalhealthassistant.core to com.google.gson;
    exports source.mentalhealthassistant;
}