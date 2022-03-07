module git.test.gittest {
    requires javafx.controls;
    requires javafx.fxml;


    opens git.test.gittest to javafx.fxml;
    exports git.test.gittest;
}