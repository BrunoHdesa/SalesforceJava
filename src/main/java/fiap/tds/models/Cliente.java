package fiap.tds.models;

import java.util.ArrayList;
import java.util.Map;

public class Cliente extends _BaseEntity{
    private String telefone;
    private int tm_empresa;
    private String email;
    private String nome;

    public Cliente() {
    }

    public Cliente(int id, String telefone, int tm_empresa, String email, String nome) {
        super(id);
        this.telefone = telefone;
        this.tm_empresa = tm_empresa;
        this.email = email;
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getTm_empresa() {
        return tm_empresa;
    }

    public void setTm_empresa(int tm_empresa) {
        this.tm_empresa = tm_empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<Boolean, ArrayList<String>> validate() {
        var errors = new ArrayList<String>();
        if (nome == null || nome.isBlank())
            errors.add("Nome do cliente não pode ser vazio");
        if (email == null)
            errors.add("Email do cliente não pode ser vazio");
        if (telefone == null)
            errors.add("Telefone do cliente não pode ser vazio");

        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "telefone='" + telefone + '\'' +
                ", tm_empresa=" + tm_empresa +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
