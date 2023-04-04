module dock.tech.keygen.hsm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires java.desktop;
    requires org.apache.logging.log4j;
    requires lombok;
    requires org.apache.commons.pool2;
    requires java.annotation;

    opens hsm to javafx.fxml;
    opens controller;
    exports hsm;
    exports controller;
    exports keygen;
}
