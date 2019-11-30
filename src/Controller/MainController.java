package Controller;

import Application.Login;
import Application.Registrar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button buttonLogar;

    @FXML
    private Button buttonRegistrar;

    @FXML
    protected void loginAction(ActionEvent event) {
        Stage stage = (Stage) buttonLogar.getScene().getWindow();;
        stage.close();
        Login.show();
    }

    @FXML
    protected void registrarAction(ActionEvent event) {
        Stage stage = (Stage) buttonRegistrar.getScene().getWindow();;
        stage.close();
        Registrar.show();
    }
}
