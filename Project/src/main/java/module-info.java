module org.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.jetbrains.annotations;

    opens org.project to javafx.fxml;
    exports org.project;
}