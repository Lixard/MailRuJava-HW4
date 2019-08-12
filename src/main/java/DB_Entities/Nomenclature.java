package DB_Entities;

import org.jetbrains.annotations.NotNull;

public final class Nomenclature {
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

    @NotNull
    public String getName() {
        return name;
    }


    public long getCode() {
        return code;
    }
}
