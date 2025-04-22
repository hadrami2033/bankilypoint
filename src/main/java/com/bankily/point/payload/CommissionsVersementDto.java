package com.bankily.point.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommissionsVersementDto {
	private long id;
	
    @NotNull
    private float commission;
    
    @NotNull
    private float min;
    
    @NotNull
    private float max;
}
