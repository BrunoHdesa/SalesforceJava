package fiap.tds.models;

import java.time.LocalDateTime;

public class Pedido extends _BaseEntity{
    private LocalDateTime data;

    public Pedido() {
    }

    public Pedido(int id, LocalDateTime data) {
        super(id);
        this.data = data;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "data=" + data +
                '}';
    }
}
