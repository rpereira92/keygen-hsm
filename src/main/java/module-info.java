module dock.tech.keygen.hsm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires java.desktop;
    requires org.apache.pdfbox;
    requires org.apache.logging.log4j;
    requires lombok;

    opens hsm to javafx.fxml;
    opens controller;
    exports hsm;
    exports controller;
    exports keygen;
}
