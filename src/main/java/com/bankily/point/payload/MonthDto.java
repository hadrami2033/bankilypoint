package com.bankily.point.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MonthDto {
    private long id;

    @NotEmpty
    private Integer code;
    
    @NotEmpty
    private String month;
}
