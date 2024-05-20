package fiap.tds.repositories;

import fiap.tds.models.Produto;
import fiap.tds.models.Usuario;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    public static List<Usuario> usuarios = new ArrayList<>();;

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553799";
    public static final String PASSWORD = "fiap24";

    // Exibir todos os Usuarios do banco de dados
    public List<Usuario> getUsuarios(){
        var lista = new ArrayList<Usuario>();
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM USUARIO");
            var rs = stat.executeQuery();
            while (rs.next()){
                var usuario = new Usuario();
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNome(rs.getString("NM_USUARIO"));
                usuario.setSenha(rs.getString("SENHA_USUARIO"));
                lista.add(usuario);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Exibir um unico Usuario pelo ID do banco de dados
    public Usuario getUsuario(int id){
        Usuario usuario = null;
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM USUARIO WHERE ID_USUARIO = ?");
            stat.setInt(1, id);
            var rs = stat.executeQuery();
            if (rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNome(rs.getString("NM_USUARIO"));
                usuario.setSenha(rs.getString("SENHA_USUARIO"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }

    // Inserir dados na tabela Usuario no banco de dados
    public void createUsuario (Usuario usuario){
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stat = conn.prepareStatement("INSERT INTO USUARIO (ID_USUARIO, NM_USUARIO, SENHA_USUARIO) VALUES (?,?,?)");
            stat.setInt(1, usuario.getId());
            stat.setString(2, usuario.getNome());
            stat.setString(3, usuario.getSenha());
            stat.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
}
