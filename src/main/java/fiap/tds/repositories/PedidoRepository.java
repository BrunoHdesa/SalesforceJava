package fiap.tds.repositories;

import fiap.tds.models.Pedido;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    public static List<Pedido> pedidos = new ArrayList<>();;

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553799";
    public static final String PASSWORD = "fiap24";

    // Exibir todos os Pedidos do banco de dados
    public List<Pedido> getPedidos(){
        var lista = new ArrayList<Pedido>();
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM PEDIDO");
            var rs = stat.executeQuery();
            while (rs.next()){
                var pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setData(LocalDateTime.parse(rs.getString("DATA_PEDIDO")));
                lista.add(pedido);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Exibir um unico Pedido pelo ID do banco de dados
    public Pedido getPedido(int id){
        Pedido pedido = null;
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM PEDIDO WHERE ID_PEDIDO = ?");
            stat.setInt(1, id);
            var rs = stat.executeQuery();
            if (rs.next()){
                pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PRODUTO"));
                pedido.setData(LocalDateTime.parse(rs.getString("DATA_PRODUTO")));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return pedido;
    }

    // Inserir dados na tabela Pedido no banco de dados
    public void createProduto (Pedido pedido){
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("INSERT INTO PEDIDO (ID_PEDIDO, DATA_PEDIDO) VALUES (?,?)");
            stat.setInt(1, pedido.getId());
            stat.setString(2, String.valueOf(pedido.getData()));
            stat.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
