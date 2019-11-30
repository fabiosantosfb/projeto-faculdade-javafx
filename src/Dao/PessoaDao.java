package Dao;

import Factory.ConnectionFactory;
import Model.Apartamento;
import Model.Pessoa;
import Model.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao implements PessoaInterface<Pessoa> {
    @Override
    public void inserir(Pessoa pessoa) {
        String pessoaSql = "INSERT INTO pessoa (nome, telefone, cpf) VALUES (?,?,?)";
        String apartamentoSql = "INSERT INTO apartamento (andar, numero, id_pessoa) VALUES (?,?,?)";

        try(Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement psmt = conn.prepareStatement(pessoaSql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, pessoa.getNome());
            psmt.setString(2, pessoa.getTelefone().getTelefone());
            psmt.setString(3, pessoa.getCpf());

            psmt.executeUpdate();
            ResultSet pkRset = psmt.getGeneratedKeys();

            if (pkRset.next()) {
                int idPessoa = pkRset.getInt(1);

                PreparedStatement psmtAp = conn.prepareStatement(apartamentoSql);

                psmtAp.setInt(1, pessoa.getApartamento().getAndar());
                psmtAp.setInt(2, pessoa.getApartamento().getNumero());
                psmtAp.setInt(3, idPessoa);
                psmtAp.execute();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir uma pessoa!");
        }
    }

    @Override
    public void alterar(Pessoa pessoa, String cpf) {
        String sql = "UPDATE pessoa SET nome=?, telefone=?, cpf=? WHERE cpf=?";
        String sqlAp = "UPDATE apartamento SET andar=?, numero=? WHERE id_pessoa=?";

        try(Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, pessoa.getNome());
            psmt.setString(2, pessoa.getTelefone().getTelefone());
            psmt.setString(3, pessoa.getCpf());
            psmt.setString(4, cpf);
            psmt.execute();

            PreparedStatement psmtAp = conn.prepareStatement(sqlAp);
            psmtAp.setInt(1, pessoa.getApartamento().getAndar());
            psmtAp.setInt(2, pessoa.getApartamento().getNumero());
            psmtAp.setInt(3, pessoa.getId());
            psmtAp.execute();
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao atualizar dados da pessoa");
        }
    }

    @Override
    public List<Pessoa> listar() {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        String sql = "SELECT * FROM pessoa ps INNER JOIN apartamento ap ON ap.id_pessoa = ps.id";

        try(Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet resultaDataBase = psmt.executeQuery();

            while(resultaDataBase.next()) {
                Pessoa pessoa = new Pessoa();
                Telefone telefone = new Telefone();
                Apartamento apartamento = new Apartamento();

                pessoa.setId(resultaDataBase.getInt("id"));
                pessoa.setNome(resultaDataBase.getString("nome"));
                pessoa.setCpf(resultaDataBase.getString("cpf"));

                telefone.setTelefone(resultaDataBase.getString("telefone"));
                pessoa.setTelefone(telefone);

                apartamento.setAndar(resultaDataBase.getInt("andar"));
                apartamento.setNumero(resultaDataBase.getInt("numero"));
                pessoa.setApartamento(apartamento);

                pessoas.add(pessoa);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao listar pessoas!");
        }

        return pessoas;
    }

    @Override
    public void deletar(String cpf) {
        String sql = "DELETE FROM pessoa WHERE cpf=?";

        try(Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, cpf);
            psmt.execute();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao deletar uma pessoa");
        }
    }

    @Override
    public List<Pessoa> buscar(Pessoa pe) {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        String sql = "SELECT * FROM pessoa ps INNER JOIN apartamento ap ON ap.id_pessoa = ps.id WHERE ps.cpf=?";

        try(Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, pe.getCpf());

            ResultSet resultaDataBase = psmt.executeQuery();

            while(resultaDataBase.next()) {
                Pessoa pessoa = new Pessoa();
                Telefone telefone = new Telefone();
                Apartamento apartamento = new Apartamento();

                pessoa.setId(resultaDataBase.getInt("id"));
                pessoa.setNome(resultaDataBase.getString("nome"));
                pessoa.setCpf(resultaDataBase.getString("cpf"));

                telefone.setTelefone(resultaDataBase.getString("telefone"));
                pessoa.setTelefone(telefone);

                apartamento.setAndar(resultaDataBase.getInt("andar"));
                apartamento.setNumero(resultaDataBase.getInt("numero"));
                pessoa.setApartamento(apartamento);

                pessoas.add(pessoa);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar uma pessoa");
        }

        return pessoas;
    }
}
