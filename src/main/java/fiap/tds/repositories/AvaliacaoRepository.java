package fiap.tds.repositories;

import fiap.tds.models.Avaliacao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoRepository {
    public static List<Avaliacao> avaliacoes = new ArrayList<>();;

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553799";
    public static final String PASSWORD = "fiap24";

    // Exibir todos as Avaliações do banco de dados
    public List<Avaliacao> getAvaliacoes(){
        var lista = new ArrayList<Avaliacao>();
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM AVALIACAO");
            var rs = stat.executeQuery();
            while (rs.next()){
                var avaliacao = new Avaliacao();
                avaliacao.setId(rs.getInt("ID_AVALIACAO"));
                avaliacao.setData(LocalDateTime.parse(rs.getString("DATA_AVALIACAO")));
                avaliacao.setNota(rs.getInt("NOTA_AVALIACAO"));
                avaliacao.setComentario(rs.getString("COMENT_AVALIACAO"));
                lista.add(avaliacao);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Exibir uma unica Avaliacao pelo ID do banco de dados
    public Avaliacao getAvaliacao(int id){
        Avaliacao avaliacao = null;
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM AVALIACAO WHERE ID_AVALIACAO = ?");
            stat.setInt(1, id);
            var rs = stat.executeQuery();
            if (rs.next()){
                avaliacao = new Avaliacao();
                avaliacao.setId(rs.getInt("ID_AVALIACAO"));
                avaliacao.setData(LocalDateTime.parse(rs.getString("DATA_AVALIACAO")));
                avaliacao.setNota(rs.getInt("NOTA_AVALIACAO"));
                avaliacao.setComentario(rs.getString("COMENT_AVALIACAO"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return avaliacao;
    }

    // Inserir dados na tabela Avaliação no banco de dados
    public void createAvaliacao (Avaliacao avaliacao){
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("INSERT INTO AVALIACAO (ID_AVALIACAO, DATA_AVALIACAO, NOTA_AVALIACAO, COMENT_AVALIACAO) VALUES (?,?,?,?)");
            stat.setInt(1, avaliacao.getId());
            stat.setString(2, String.valueOf(avaliacao.getData()));
            stat.setInt(3, avaliacao.getNota());
            stat.setString(4, avaliacao.getComentario());
            stat.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
