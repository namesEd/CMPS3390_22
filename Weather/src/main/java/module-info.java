 module a4.ekyles.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;

    opens a4.ekyles.weather to javafx.fxml;
    exports a4.ekyles.weather;
}