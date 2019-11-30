package Controller;

import Controller.List.ListViewCell;
import Dao.PessoaDao;
import Model.Apartamento;
import Model.Pessoa;
import Model.Telefone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Window;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class VizualizarPropietarioController {
    @FXML
    private Button buttonListar;
    @FXML
    private ListView listView;
    private List<String> stringList;
    private ObservableList observableList;

    private PessoaDao pessoaDao;

    private List<Pessoa> pessoas;

    public VizualizarPropietarioController() {
        this.observableList = FXCollections.observableArrayList();
        this.stringList = new ArrayList<>();
        this.pessoaDao = new PessoaDao();
    }

    public void listarAction(ActionEvent event) {
        stringList.clear();
        this.pessoas = this.pessoaDao.listar();

        for(Pessoa pessoa : this.pessoas) {
            stringList.add(pessoa.toString());
        }

        observableList.setAll(stringList);
        listView.setItems(observableList);
        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new ListViewCell();
            }
        });
    }

    public void handleTabVizualizarPropietario() {
    }
}
