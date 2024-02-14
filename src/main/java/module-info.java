module com.ia.planda {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.ia.planda to javafx.fxml;
    exports com.ia.planda;
}