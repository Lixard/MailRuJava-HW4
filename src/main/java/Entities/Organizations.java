package Entities;

import lombok.Data;

@Data
public class Organizations {
    private long id;
    private String name;
    private long INN;
    private long settlementAccount;
}
