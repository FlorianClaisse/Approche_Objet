module org.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.project to javafx.fxml;
    exports org.project;
}