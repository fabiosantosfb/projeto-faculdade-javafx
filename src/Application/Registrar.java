package Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Registrar {
    public static void show() {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(Registrar.class.getResource("../Resource/Layout/Xml/Autenticacao/registrar.fxml"));
            stage.setTitle("GC - Registrar Usuário");
            stage.setScene(new Scene(root, 600, 300));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
