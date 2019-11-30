package Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    public static void show() {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(Login.class.getResource("../Resource/Layout/Xml/Autenticacao/login.fxml"));
            stage.setTitle("GC - Login de usuário");
            stage.setScene(new Scene(root, 600, 300));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
