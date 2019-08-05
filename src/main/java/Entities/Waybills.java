package Entities;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Waybills {
    private long id;
    private long number;
    private Timestamp date;
    private String organizationSend;
}
