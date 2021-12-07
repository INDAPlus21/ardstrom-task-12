module com.example.ardstromtask12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ardstromtask12 to javafx.fxml;
    exports com.example.ardstromtask12;
}