package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GerenciarBancoDeDados {

    private static String uri = "localhost";
    private static String port = "3306";
    private static String schema = "gerencia_condominio";
    private static String login = "gerencia_condominio_user";
    private static String password = "@*1206Fb";
    private static String url = "jdbc:mysql://"+uri+":"+port+"/";

    private static void criarSchema() {
        String sql = "CREATE SCHEMA IF NOT EXISTS " + schema + ";";
        try(Connection conn = DriverManager.getConnection(url, login, password)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
        }catch (SQLException e) {
            System.out.println("Erro ao criar o Schema");
            System.out.println("SQLException - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void criarTabelaPessoa() {
        String sql = "CREATE TABLE IF NOT EXISTS pessoa("
                + "id int NOT NULL AUTO_INCREMENT,"
                + "nome varchar(100),"
                + "telefone varchar(9),"
                + "cpf varchar(15),"
                + "PRIMARY KEY(id),"
                + "CONSTRAINT UC_pessoa UNIQUE (id, telefone, cpf))";

        try(Connection conn = DriverManager.getConnection(url+schema, login, password)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
        }catch (SQLException e) {
            System.out.println("Erro ao criar a tabela pessoa");
            System.out.println("SQLException - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void criarTabelaApartamento() {
        String sql = "CREATE TABLE IF NOT EXISTS apartamento("
                + "id int NOT NULL AUTO_INCREMENT,"
                + "andar int(11),"
                + "numero int(11),"
                + "id_pessoa int(11),"
                + "PRIMARY KEY(id),"
                + "CONSTRAINT UC_apartamento UNIQUE (id, numero),"
                + "FOREIGN KEY (id_pessoa) REFERENCES pessoa(id) ON DELETE CASCADE ON UPDATE CASCADE)";

        try(Connection conn = DriverManager.getConnection(url+schema, login, password)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
        }catch (SQLException e) {
            System.out.println("Erro ao criar a tabela apartamento");
            System.out.println("SQLException - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void criarTabelaUsuario() {
        String sql = "CREATE TABLE IF NOT EXISTS usuario("
                + "id int NOT NULL AUTO_INCREMENT,"
                + "nome varchar(100),"
                + "email varchar(255),"
                + "senha varchar(255),"
                + "PRIMARY KEY(id),"
                + "CONSTRAINT UC_pessoa UNIQUE (id, email))";

        try(Connection conn = DriverManager.getConnection(url+schema, login, password)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
        }catch (SQLException e) {
            System.out.println("Erro ao criar a tabela usuário");
            System.out.println("SQLException - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void main() {
        criarSchema();
        criarTabelaPessoa();
        criarTabelaApartamento();
        criarTabelaUsuario();
    }
}
