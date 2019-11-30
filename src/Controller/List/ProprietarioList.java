package Controller.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ProprietarioList {
    @FXML
    private HBox hBox;
    @FXML
    private Label proprietario;
    @FXML
    private Label telefone;
    @FXML
    private Label cpf;
    @FXML
    private Label andar;
    @FXML
    private Label numero;

    public ProprietarioList()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../Resource/Layout/Xml/List/proprietario-list.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(String string)
    {
        proprietario.setText(string);
    }

    public HBox getBox()
    {
        return hBox;
    }
}
