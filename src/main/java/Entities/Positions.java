package Entities;

import lombok.Data;

@Data
public class Positions {
    private long id;
    private long price;
    private String nomenclature;
    private long amount;
    private long waybills_id;
}
