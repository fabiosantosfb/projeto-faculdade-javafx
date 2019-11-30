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
import javafx.stage.Window;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class AtualizarPropietarioController {
    @FXML
    private TextField cpfBusca;
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
    private Button buttonListar;
    @FXML
    private Button buttonAtualizar;
    @FXML
    private Button buttonBuscar;
    @FXML
    private Button buttonRemover;
    @FXML
    private ListView listView;
    private List<String> stringList;
    private ObservableList observableList;

    private PessoaDao pessoaDao;
    private Pessoa pessoa;
    private Telefone telefonePessoa;
    private Apartamento apartamento;
    private int id_pessoa;

    private List<Pessoa> pessoas;

    public AtualizarPropietarioController() {
        this.observableList = FXCollections.observableArrayList();
        this.stringList = new ArrayList<>();
        this.pessoaDao = new PessoaDao();
        this.pessoa = new Pessoa();
    }

    public void listarAction(ActionEvent event) {
        stringList.clear();
        this.pessoas = this.pessoaDao.listar();

        setListView();
    }

    public void buscarAction(ActionEvent event) {
        Window owner = buttonBuscar.getScene().getWindow();

        if(cpfBusca.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulário Error!",
                    "Por favor informe o cpf do proprietário.");
            return;
        }
        stringList.clear();

        pessoa.setCpf(cpfBusca.getText());
        this.pessoas = this.pessoaDao.buscar(pessoa);

        if (this.pessoas.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Resultado Busca!",
                    "Não foi encontrado nenhum proprietário com esse cpf.");
            return;
        }

        preencherCampos();
        setListView();
    }

    public void atualizarAction(ActionEvent event) {
        Window owner = buttonAtualizar.getScene().getWindow();
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
        if(cpfBusca.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulário Error!",
                    "Por favor informe o cpf atual do proprietário.");
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
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Atualização!",
                "Atualização efetuada com sucesso.");

        this.registrarPessoa();
        this.limparCampos();
    }

    public void removerAction(ActionEvent event) {
        Window owner = buttonRemover.getScene().getWindow();

        if(cpfBusca.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulário Error!",
                    "Por favor informe o cpf atual do proprietário.");
            return;
        }

        this.pessoaDao = new PessoaDao();
        this.pessoaDao.deletar(cpfBusca.getText());
        this.limparCampos();

        this.stringList.clear();
        this.pessoas = this.pessoaDao.listar();

        setListView();

        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Remoção!",
                "Proprietário removido com sucesso.");
    }

    public void handleTabRegistrarPropietario() {
    }

    private void setListView() {
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
    private void registrarPessoa() {
        pessoaDao = new PessoaDao();

        pessoaDao.alterar(this.buildPessoa(), cpfBusca.getText());
    }

    private void limparCampos() {
        nome.clear();
        cpf.clear();
        cpfBusca.clear();
        telefone.clear();
        andar_apartamento.clear();
        numero_apartamento.clear();
    }

    private void preencherCampos() {
        this.id_pessoa = this.pessoas.get(0).getId();
        nome.setText(this.pessoas.get(0).getNome());
        cpf.setText(this.pessoas.get(0).getCpf());
        telefone.setText(this.pessoas.get(0).getTelefone().getTelefone());
        andar_apartamento.setText(String.valueOf(this.pessoas.get(0).getApartamento().getAndar()));
        numero_apartamento.setText(String.valueOf(this.pessoas.get(0).getApartamento().getNumero()));
    }

    /**
     * @return Pessoa
     */
    private Pessoa buildPessoa() {
        this.pessoa =  new Pessoa();
        pessoa.setId(this.id_pessoa);
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
