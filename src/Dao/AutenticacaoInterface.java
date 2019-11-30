package Dao;

import Model.Usuario;

import javax.naming.AuthenticationException;
import java.util.List;

public interface AutenticacaoInterface<T> {
    void inserir(Usuario elemento) throws AuthenticationException;
    void alterar(Usuario elemento, String email) throws AuthenticationException;
    List<Usuario> listar() throws AuthenticationException;
    void deletar(String email) throws AuthenticationException;
    Usuario login(Usuario elemento) throws AuthenticationException;
}
