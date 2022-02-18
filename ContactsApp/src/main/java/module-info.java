module a3.ekyles.contactsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens a3.ekyles.contactsapp to javafx.fxml;
    exports a3.ekyles.contactsapp;
}