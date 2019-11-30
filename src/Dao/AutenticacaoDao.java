package Dao;

import Factory.ConnectionFactory;
import Model.Pessoa;
import Model.Telefone;
import Model.Usuario;

import javax.naming.AuthenticationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AutenticacaoDao implements AutenticacaoInterface<Usuario> {

    @Override
    public void inserir(Usuario usuario) throws AuthenticationException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES" +
                "(?,?,?)";

        try(Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, usuario.getNome());
            psmt.setString(2, usuario.getEmail());
            psmt.setString(3, usuario.getSenha());
            psmt.execute();
        }catch (SQLException e) {
            throw new AuthenticationException("Erro ao registrar usuário! - " + e.getMessage());
        }
    }

    @Override
    public void alterar(Usuario user, String email) throws AuthenticationException {
        String sql = "UPDATE usuario SET nome=?, senha=?, email=? WHERE email=?";

        try(Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getNome());
            psmt.setString(2, user.getSenha());
            psmt.setString(3, user.getEmail());
            psmt.setString(4, email);
            psmt.execute();
        }catch(SQLException e) {
            throw new AuthenticationException("Erro ao atualizar usuário! - " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> listar() {
        return null;
    }

    @Override
    public void deletar(String email) {

    }

    @Override
    public Usuario login(Usuario user) throws AuthenticationException {
        String sql = "SELECT nome FROM usuario WHERE email=? and  senha=?";

        try(Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getEmail());
            psmt.setString(2, user.getSenha());

            ResultSet resultaDataBase = psmt.executeQuery();
            Usuario usuario = new Usuario();

            while(resultaDataBase.next()) {
                usuario.setNome(resultaDataBase.getString("nome"));

                return usuario;
            }

            return null;
        }catch (SQLException e) {
            throw new AuthenticationException("Erro efetuar o login. - "  + e.getMessage());
        }
    }
}
