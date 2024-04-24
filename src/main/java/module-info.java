module org.battlegame.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens org.battlegame.demo to javafx.fxml;
    exports org.battlegame.demo;
}