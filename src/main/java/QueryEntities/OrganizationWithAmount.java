package QueryEntities;

import org.jetbrains.annotations.NotNull;

public final class OrganizationWithAmount {

    private long id;
    @NotNull
    private String name;

    private long inn;

    private long settlement_acc;

    private long amount;

    public OrganizationWithAmount(long id, @NotNull String name, long inn, long settlement_acc, long amount) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.settlement_acc = settlement_acc;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\nid: " + id + " name: " + name + " inn:" + inn + " settlement_acc: " + settlement_acc + " amount: " + amount;
    }
}
