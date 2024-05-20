package fiap.tds.repositories;

import fiap.tds.models.Consultor;
import fiap.tds.models.Produto;

import java.io.Console;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultorRepository {
    public static List<Consultor> consultores = new ArrayList<>();;

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553799";
    public static final String PASSWORD = "fiap24";

    // Exibir todos os Consultores do banco de dados
    public List<Consultor> getConsultores(){
        var lista = new ArrayList<Consultor>();
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM CONSULTOR");
            var rs = stat.executeQuery();
            while (rs.next()){
                var consultor = new Consultor();
                consultor.setId(rs.getInt("ID_CONSULTOR"));
                consultor.setNome(rs.getString("NM_CONSULTOR"));
                consultor.setEmail(rs.getString("EMAIL_CONSULTOR"));
                consultor.setTelefone(rs.getString("TELEF_CONSULTOR"));
                lista.add(consultor);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Exibir um unico Consultor pelo ID do banco de dados
    public Consultor getConsultor(int id){
        Consultor consultor = null;
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM CONSULTOR WHERE ID_CONSULTOR = ?");
            stat.setInt(1, id);
            var rs = stat.executeQuery();
            if (rs.next()){
                consultor = new Consultor();
                consultor.setId(rs.getInt("ID_CONSULTOR"));
                consultor.setNome(rs.getString("NM_CONSULTOR"));
                consultor.setEmail(rs.getString("EMAIL_CONSULTOR"));
                consultor.setTelefone(rs.getString("TELEF_CONSULTOR"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return consultor;
    }

    // Inserir dados na tabela Consultor no banco de dados
    public void createConsultor (Consultor consultor){
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("INSERT INTO CONSULTOR (ID_CONSULTOR, NM_CONSULTOR, EMAIL_CONSULTOR, TELEF_CONSULTOR) VALUES (?,?,?,?)");
            stat.setInt(1, consultor.getId());
            stat.setString(2, consultor.getNome());
            stat.setString(3, consultor.getEmail());
            stat.setString(4, consultor.getTelefone());
            stat.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
