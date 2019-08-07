package Entities;

import org.jetbrains.annotations.NotNull;

public class Organizations {

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

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public long getSettlement_acc() {
        return settlement_acc;
    }

    public void setSettlement_acc(long settlement_acc) {
        this.settlement_acc = settlement_acc;
    }
}
