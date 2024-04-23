module org.battlegame.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.battlegame.demo to javafx.fxml;
    exports org.battlegame.demo;
}