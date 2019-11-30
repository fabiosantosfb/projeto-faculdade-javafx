package Model;

import java.util.ArrayList;

public class Condominio {
    private String nome;
    private Endereco endereco;
    private ArrayList<Bloco> blocos;
    private float valor;
    private boolean gas;
    private boolean internet;
    private boolean piscina;
    private boolean energiaSolar;
    private boolean guarita;

    public Condominio() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Bloco> getBlocos() {
        return blocos;
    }

    public void setBlocos(Bloco blocos) {
        this.blocos.add(blocos);
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean isGas() {
        return gas;
    }

    public void setGas(boolean gas) {
        this.gas = gas;
    }

    public boolean isInternet() {
        return internet;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    public boolean isPiscina() {
        return piscina;
    }

    public void setPiscina(boolean piscina) {
        this.piscina = piscina;
    }

    public boolean isEnergiaSolar() {
        return energiaSolar;
    }

    public void setEnergiaSolar(boolean energiaSolar) {
        this.energiaSolar = energiaSolar;
    }

    public boolean isGuarita() {
        return guarita;
    }

    public void setGuarita(boolean guarita) {
        this.guarita = guarita;
    }
}
