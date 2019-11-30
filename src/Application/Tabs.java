package Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Tabs {
    public static void show() {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(Tabs.class.getResource("../Resource/Layout/Xml/Tabs/tabs.fxml"));
            stage.setTitle("GC - Login de usuário");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
