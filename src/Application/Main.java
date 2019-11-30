package Application;

import Factory.GerenciarBancoDeDados;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static GerenciarBancoDeDados gerenciarBancoDeDados;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("../Resource/Layout/Xml/Main.fxml"));
        stage.setTitle("GC - Sistemas Gerenciamento de Condominios");
        stage.setScene(new Scene(root, 600, 300));
        stage.show();

        gerenciarBancoDeDados = new GerenciarBancoDeDados();
        gerenciarBancoDeDados.main();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
