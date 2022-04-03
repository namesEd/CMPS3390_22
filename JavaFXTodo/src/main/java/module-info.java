module a8.ekyles.javafxtodo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens a8.ekyles.javafxtodo to javafx.fxml;
    exports a8.ekyles.javafxtodo;
}