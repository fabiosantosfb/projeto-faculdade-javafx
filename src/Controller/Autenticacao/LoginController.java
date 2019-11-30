package Controller.Autenticacao;

import Application.Alert.AlertHelper;
import Application.Registrar;
import Application.Tabs;
import Dao.AutenticacaoDao;
import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.naming.AuthenticationException;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField senha;

    @FXML
    private Button buttonLogar;

    @FXML
    private Button buttonRegistrar;

    @FXML
    protected void loginAction(ActionEvent event) {
        Window owner = buttonLogar.getScene().getWindow();
        if(email.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Preencha campo email!",
                    "Por favor informe seu email.");
            return;
        }
        if(senha.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Preencha campo senha!",
                    "Por favor informe sua senha.");
            return;
        }

        this.autenticacaoUser(owner);
    }

    @FXML
    protected void registrarAction(ActionEvent event) {
        Stage stage = (Stage) buttonRegistrar.getScene().getWindow();;
        stage.close();
        Registrar.show();
    }

    public void autenticacaoUser(Window owner) {
        Usuario usuario = new Usuario();

        usuario.setEmail(email.getText());
        usuario.setSenha(senha.getText());

        AutenticacaoDao autenticacao = new AutenticacaoDao();

        try {
            usuario = autenticacao.login(usuario);

            if (null != usuario) {
                Stage stage = (Stage) owner;
                stage.close();
                Tabs.show();
            } else {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Login Inválido",
                        "Email ou senha inválida.");
            }
        } catch (AuthenticationException ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erro de login!",
                    ex.getMessage());
        }
    }
}
