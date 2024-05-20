package fiap.tds.models;

import java.time.LocalDateTime;

public abstract class _BaseEntity {
    protected int id;
    protected final LocalDateTime criadoEm;

    public _BaseEntity() {
        this.criadoEm = LocalDateTime.now();
    }

    public _BaseEntity(int id) {
        this.id = id;
        this.criadoEm = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }


    @Override
    public String toString() {
        return "_BaseEntity{" +
                "id=" + id +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
