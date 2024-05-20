package fiap.tds.repositories;

import fiap.tds.models.Produto;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    public static List<Produto> produtos = new ArrayList<>();;

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553799";
    public static final String PASSWORD = "fiap24";

    // Exibir todos os Produtos do banco de dados
    public List<Produto> getProdutos(){
        var lista = new ArrayList<Produto>();
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM PRODUTO");
            var rs = stat.executeQuery();
            while (rs.next()){
                var produto = new Produto();
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NM_PRODUTO"));
                produto.setDescricao(rs.getString("DESC_PRODUTO"));
                produto.setPreco(rs.getDouble("PRECO_PRODUTO"));
                lista.add(produto);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Exibir um unico Produto pelo ID do banco de dados
    public Produto getProduto(int id){
        Produto produto = null;
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM PRODUTO WHERE ID_PRODUTO = ?");
            stat.setInt(1, id);
            var rs = stat.executeQuery();
            if (rs.next()){
                produto = new Produto();
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NM_PRODUTO"));
                produto.setDescricao(rs.getString("DESC_PRODUTO"));
                produto.setPreco(rs.getDouble("PRECO_PRODUTO"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    // Inserir dados na tabela Produto no banco de dados
    public void createProduto (Produto produto){
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("INSERT INTO PRODUTO (ID_PRODUTO, NM_PRODUTO, DESC_PRODUTO, PRECO_PRODUTO) VALUES (?,?,?,?)");
            stat.setInt(1, produto.getId());
            stat.setString(2, produto.getNome());
            stat.setString(3, produto.getDescricao());
            stat.setDouble(4, produto.getPreco());
            stat.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
