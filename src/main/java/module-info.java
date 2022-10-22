module bomberman.btl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens bomberman.btl to javafx.fxml;
    exports bomberman.btl;
}