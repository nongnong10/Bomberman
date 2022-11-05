module bomberman.btl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens bomberman.btl to javafx.fxml;
    exports bomberman.btl.main;
    opens bomberman.btl.main to javafx.fxml;
    exports bomberman.btl.input;
    opens bomberman.btl.input to javafx.fxml;
}