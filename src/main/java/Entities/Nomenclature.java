package Entities;

import org.jetbrains.annotations.NotNull;

public class Nomenclature {
    @NotNull
    private long id;
    @NotNull
    private String name;
    @NotNull
    private long code;

    public Nomenclature(long id, @NotNull String name, long code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return id + ", " + name +
    }
}
