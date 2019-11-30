package Dao;

import java.util.List;

public interface PessoaInterface<T> {
    void inserir(T elemento);
    void alterar(T elemento, String cpf);
    List<T> listar();
    void deletar(String cpf);
    List<T> buscar(T elemento);
}
