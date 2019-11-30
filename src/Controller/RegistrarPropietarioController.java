package Controller;

import Controller.List.ListViewCell;
import Dao.PessoaDao;
import Model.Apartamento;
import Model.Pessoa;
import Model.Telefone;
import Application.Alert.AlertHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class RegistrarPropietarioController {
    @FXML
    private TextField nome;
    @FXML
    private TextField cpf;
    @FXML
    private TextField telefone;
    @FXML
    private TextField andar_apartamento;
    @FXML
    private TextField numero_apartamento;
    @FXML
    private Button buttonRegistrar;
    @FXML
    private Button buttonSair;

    private PessoaDao pessoaDao;
    private Pessoa pessoa;
    private Telefone telefonePessoa;
    private Apartamento apartamento;

    @FXML
    private ListView listView;
    private List<String> stringList;
    private ObservableList observableList;

    public RegistrarPropietarioController() {
        this.observableList = FXCollections.observableArrayList();
        this.stringList = new ArrayList<>();
    }

    public void handleTabRegistrarPropietario() {

    }

    public void registrarAction(ActionEvent event) throws Exception {
        Window owner = buttonRegistrar.getScene().getWindow();
        if(nome.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulário Error!",
                    "Por favor informe o nome do proprietário.");
            return;
        }
        if(cpf.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulário Error!",
                    "Por favor informe o cpf do proprietário.");
            return;
        }
        if(telefone.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulário Error!",
                    "Por favor informe o telefone para contato do proprietário.");
            return;
        }
        if(andar_apartamento.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulário Error!",
                    "Por favor informe o andar do apartamento.");
            return;
        }
        if(numero_apartamento.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulário Error!",
                    "Por favor informe o número do apartamento.");
            return;
        }
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Registrado Com Successo!",
                "Registrado proprietário do apartamento com sucesso.");

        this.registrarPessoa();
        this.setListView();
        this.limparCampos();
    }

    public void sairAction(ActionEvent event) {
        Stage stage = (Stage) buttonSair.getScene().getWindow();
        stage.close();
    }

    public void setListView() {
        stringList.add(this.buildPessoa().toString());

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

    private void registrarPessoa() {
        pessoaDao = new PessoaDao();

        pessoaDao.inserir(this.buildPessoa());
    }

    private void limparCampos() {
        nome.clear();
        cpf.clear();
        telefone.clear();
        andar_apartamento.clear();
        numero_apartamento.clear();
    }

    /**
     * @return Pessoa
     */
    private Pessoa buildPessoa() {
        this.pessoa =  new Pessoa();
        pessoa.setNome(nome.getText());
        pessoa.setCpf(cpf.getText());

        this.telefonePessoa = new Telefone();
        this.telefonePessoa.setTelefone(telefone.getText());
        pessoa.setTelefone(this.telefonePessoa);

        this.apartamento = new Apartamento();
        this.apartamento.setAndar(Integer.parseInt(andar_apartamento.getText()));
        this.apartamento.setNumero(Integer.parseInt(numero_apartamento.getText()));
        pessoa.setApartamento(this.apartamento);

        return pessoa;
    }
}
