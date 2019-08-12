package QueryEntities;

import org.jetbrains.annotations.NotNull;

public final class OrganizationWithPrice {

    private long id;
    @NotNull
    private String name;

    private long inn;

    private long settlement_acc;

    private long price;

    public OrganizationWithPrice(long id, @NotNull String name, long inn, long settlement_acc, long price) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.settlement_acc = settlement_acc;
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nid: " + id + " name: " + name + " inn:" + inn + " settlement_acc: " + settlement_acc + " price: " + price;
    }
}
