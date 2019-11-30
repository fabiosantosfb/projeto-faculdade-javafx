package Model;

import java.util.ArrayList;

public class Familia {
    private Pessoa pai;
    private Pessoa mae;
    private ArrayList<Pessoa> filhos;

    public Familia() {
    }

    public Pessoa getPai() {
        return pai;
    }

    public void setPai(Pessoa pai) {
        this.pai = pai;
    }

    public Pessoa getMae() {
        return mae;
    }

    public void setMae(Pessoa mae) {
        this.mae = mae;
    }

    public ArrayList<Pessoa> getFilhos() {
        return filhos;
    }

    public void setFilhos(Pessoa filhos) {
        this.filhos.add(filhos);
    }
}
