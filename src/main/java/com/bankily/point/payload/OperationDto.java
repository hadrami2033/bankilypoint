package com.bankily.point.payload;

import lombok.Data;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class OperationDto {
    private long id;
    
    @NotEmpty
    private String trsId;
    
    @NotEmpty
    @Size(min = 8, max = 8, message = "téléphone doit etre 8 caractéres")
    private String phone;
    
    @NotNull
    private float amount;
    
    @NotNull
    private float commission;
    
    @NotEmpty
    private String status;
    
    @NotNull
    private Date dateCreation;
    
    @NotNull
    private Date updatedAt;

    private String description;
    
    @NotNull
    private Long typeId;
    private TypeDto type;
    
    @NotNull
    private Long monthId;
    private MonthDto month;
    
    @NotNull
    private Long yearId;
    private YearDto year;
}
