package Model;

import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private String cpf;
    private int id;
    private ArrayList<Telefone> telefones;
    private ArrayList<Apartamento> apartamentos;
    private Telefone telefone;
    private Apartamento apartamento;

    public Pessoa() {
        this.telefones = new ArrayList<Telefone>();
        this.apartamentos = new ArrayList<Apartamento>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void addTelefone(Telefone telefones) {
        this.telefones.add(telefones);
    }

    public ArrayList<Apartamento> getApartamentos() {
        return apartamentos;
    }

    public void addApartamento(Apartamento apartamento) {
        this.apartamentos.add(apartamento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    @Override
    public String toString() {
        return "Proprietário: " + nome + " - Telefone: " + telefone.getTelefone() + " - CPF: " + cpf +
                " - Andar: " + apartamento.getAndar() + " - Numero: " + apartamento.getNumero();
    }
}
