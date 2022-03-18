module a6.ekyles.fxchat.fxchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens a6.ekyles.fxchat.fxchat to javafx.fxml;
    exports a6.ekyles.fxchat.fxchat;
}