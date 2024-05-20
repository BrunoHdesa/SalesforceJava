package fiap.tds.models;

import java.time.LocalDateTime;

public class Avaliacao extends _BaseEntity{
    private LocalDateTime data;
    private int nota;
    private String comentario;

    public Avaliacao() {
    }

    public Avaliacao(int id, LocalDateTime data, int nota, String comentario) {
        super(id);
        this.data = data;
        this.nota = nota;
        this.comentario = comentario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Avalicao{" +
                "data=" + data +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
