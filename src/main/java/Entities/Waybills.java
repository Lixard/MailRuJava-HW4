package Entities;

import org.jetbrains.annotations.NotNull;

;import java.util.Date;


public class Waybills {
    private long org_send_id;
    private long number;
    @NotNull
    private Date date;

    public Waybills(long org_send_id, long number, @NotNull Date date) {
        this.org_send_id = org_send_id;
        this.number = number;
        this.date = date;
    }

    public long getOrg_send_id() {
        return org_send_id;
    }

    public void setOrg_send_id(long org_send_id) {
        this.org_send_id = org_send_id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @NotNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }
}
