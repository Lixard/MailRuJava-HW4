package QueryEntities;

import java.util.Date;

public class DataAmountPrice {

    private Date date;

    private long amount;

    private long price;

    public DataAmountPrice(Date date, long amount, long price) {
        this.date = date;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public String toString() {
        return "\ndate: " + date + " amount: " + amount + " price: " + price;
    }
}
