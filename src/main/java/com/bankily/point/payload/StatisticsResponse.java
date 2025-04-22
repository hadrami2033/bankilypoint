package com.bankily.point.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {
    private double commissions;
    private double montantValid;
    private double montantCanceled;
    private int operationValid;
    private int operationCanceled;
    private int codeMonth;
    private double montantRetrait;
    private double montantVersement;
    private int operationRetrait;
    private int operationVersement;
}
