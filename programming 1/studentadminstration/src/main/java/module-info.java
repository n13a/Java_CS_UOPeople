module org.example.studentadminstration {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.studentadminstration to javafx.fxml;
    exports org.example.studentadminstration;
}