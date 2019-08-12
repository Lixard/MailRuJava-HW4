package DB_Entities;

public final class Positions {
    private long nomenclature_id;
    private long waybill_id;
    private long price;
    private long amount;

    public Positions(long nomenclature_id, long waybill_id, long price, long amount) {
        this.nomenclature_id = nomenclature_id;
        this.waybill_id = waybill_id;
        this.price = price;
        this.amount = amount;
    }

    public long getNomenclature_id() {
        return nomenclature_id;
    }

    public long getWaybill_id() {
        return waybill_id;
    }

    public long getPrice() {
        return price;
    }

    public long getAmount() {
        return amount;
    }

}
