package Controller.Autenticacao;

import Application.Alert.AlertHelper;
import Application.Login;
import Dao.AutenticacaoDao;
import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.naming.AuthenticationException;

public class RegistrarController {
    @FXML
    private TextField email;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField nome;

    @FXML
    private Button buttonRegistrar;

    @FXML
    private Button buttonLogar;

    @FXML
    protected void registrarAction(ActionEvent event) {
        Window owner = buttonRegistrar.getScene().getWindow();
        if(nome.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Preencha campo nome!",
                    "Por favor informe seu nome.");
            return;
        }
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

        this.registrarUser(owner);
    }

    @FXML
    protected void logarAction() {
        Stage stage = (Stage) buttonLogar.getScene().getWindow();;
        stage.close();
        Login.show();
    }

    public void registrarUser(Window owner) {
        Usuario usuario = new Usuario();

        usuario.setEmail(email.getText());
        usuario.setSenha(senha.getText());
        usuario.setNome(nome.getText());

        AutenticacaoDao autenticacao = new AutenticacaoDao();

        try {
            autenticacao.inserir(usuario);
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Registro de usuário!",
                    "Usuário registrado com sucesso.");

            logarAction();
        } catch (AuthenticationException ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Erro no registro!",
                    ex.getMessage());
        }
    }
}
