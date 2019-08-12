package DB_Entities;

import org.jetbrains.annotations.NotNull;

public final class Organizations {

    private long id;
    @NotNull
    private String name;

    private long inn;

    private long settlement_acc;


    public Organizations(long id, @NotNull String name, long inn, long settlement_acc) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.settlement_acc = settlement_acc;
    }

    public long getId() {
        return id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public long getInn() {
        return inn;
    }

    public long getSettlement_acc() {
        return settlement_acc;
    }
}
