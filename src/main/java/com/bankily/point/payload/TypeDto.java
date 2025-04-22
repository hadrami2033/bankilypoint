package com.bankily.point.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TypeDto {
    private long id;
    
    @NotEmpty
    private String label;
}
