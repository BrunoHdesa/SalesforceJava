package fiap.tds.repositories;

import fiap.tds.models.Cliente;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    public static List<Cliente> clientes = new ArrayList<>();;

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553799";
    public static final String PASSWORD = "fiap24";

    // Exibir todos os Clientes do banco de dados
    public List<Cliente> getClientes(){
        var lista = new ArrayList<Cliente>();
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM CLIENTE");
            var rs = stat.executeQuery();
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("ID_CLIE"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setTm_empresa(rs.getInt("TM_EMPRESA"));
                cliente.setEmail(rs.getString("EMAIL_CLIE"));
                cliente.setNome(rs.getString("NM_CLIE"));
                lista.add(cliente);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Exibir um unico Cliente pelo ID do banco de dados
    public Cliente getCliente(int id){
        Cliente cliente = null;
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?");
            stat.setInt(1, id);
            var rs = stat.executeQuery();
            if (rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt("ID_CLIE"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setTm_empresa(rs.getInt("TM_EMPRESA"));
                cliente.setEmail(rs.getString("EMAIL_CLIE"));
                cliente.setNome(rs.getString("NM_CLIE"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }

    // Inserir dados na tabela Cliente no banco de dados
    public void createCliente (Cliente cliente){
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("INSERT INTO CLIENTE (ID_CLIE, TELEFONE, TM_EMPRESA, EMAIL_CLIE, NM_CLIE) VALUES (?,?,?,?,?)");
            stat.setInt(1, cliente.getId());
            stat.setString(2, cliente.getTelefone());
            stat.setInt(3, cliente.getTm_empresa());
            stat.setString(4, cliente.getEmail());
            stat.setString(5, cliente.getNome());
            stat.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
