module com.infomanagers.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    opens com.infomanagers.app to javafx.fxml;
    exports com.infomanagers.app;
    exports com.infomanagers.app.Model;
    exports com.infomanagers.app.Controller;
    opens com.infomanagers.app.Controller to com.google.gson, javafx.fxml;
    opens com.infomanagers.app.Model to com.google.gson, javafx.fxml;
}