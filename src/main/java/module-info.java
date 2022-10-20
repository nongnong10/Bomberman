module bomberman.btl {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens bomberman.btl to javafx.fxml;
    exports bomberman.btl;
}