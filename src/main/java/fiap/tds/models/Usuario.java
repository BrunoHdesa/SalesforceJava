package fiap.tds.models;

import java.util.ArrayList;
import java.util.Map;

public class Usuario extends _BaseEntity{
    private String nome;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String nome, String senha) {
        super(id);
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Map<Boolean, ArrayList<String>> validate() {
        var errors = new ArrayList<String>();
        if (nome == null || nome.isBlank())
            errors.add("Nome do Cadastro não pode ser vazio");
        if (senha == null)
            errors.add("Senha do cadastro não pode ser vazio");

        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
