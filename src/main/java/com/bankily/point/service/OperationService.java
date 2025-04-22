package com.bankily.point.service;
import java.util.List;

import java.sql.Date;

import com.bankily.point.payload.OperationDto;
import com.bankily.point.payload.OperationResponse;
import com.bankily.point.payload.StatisticsResponse;
import com.bankily.point.payload.YearSolde;

public interface OperationService {
	OperationDto add(OperationDto operationDto);

	OperationDto get(Long operationId);

    OperationResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);

    OperationDto update(OperationDto operationDto, Long operationId);
    
    List<StatisticsResponse> yearStatistics(Long yearId);

    void delete(Long operationId);
    
    YearSolde getYearSolde(Long yearId, String status);
    
	YearSolde getYearSoldeByType(Long yearId, String status, Long typeId);

    OperationResponse getByType(String status, Long typeId,Long monthId, Long yearId,int pageNo, int pageSize, String sortBy, String sortDir);

    OperationResponse findByMonthIdAndYearId(Long monthId, Long yearId,int pageNo, int pageSize, String sortBy, String sortDir);

    OperationResponse findByDateCreation(Date date,int pageNo, int pageSize, String sortBy, String sortDir);
    
    OperationResponse findByStatusAndDateCreation(String status,Date date,int pageNo, int pageSize, String sortBy, String sortDir);

    OperationResponse findAllByDateBetween(Date startDate, Date endDate,int pageNo, int pageSize, String sortBy, String sortDir);

    OperationResponse findByStatusAndMonthIdAndYearId(String status, Long monthId, Long yearId,int pageNo, int pageSize, String sortBy, String sortDir);

    OperationResponse Versements(String status, Long monthId, Long yearId, String search, int pageNo, int pageSize, String sortBy, String sortDir);
	
    OperationResponse Entrees(String status, Long monthId, Long yearId, int pageNo, int pageSize, String sortBy, String sortDir);

    OperationResponse Wallets(String status, Long monthId, Long yearId, int pageNo, int pageSize, String sortBy, String sortDir);

    OperationResponse Withdrawals(String status, Long monthId, Long yearId, String search, int pageNo, int pageSize, String sortBy, String sortDir);
    
    OperationResponse Withdrawals2(String status, Long monthId, Long yearId, String search, int pageNo, int pageSize, String sortBy, String sortDir);

	
//    List<OperationDto> findAllByDateBetween(Date startDate, Date endDate);
//    
//    List<OperationDto> findByMonthIdAndYearId(Long monthId, Long yearId);
//    
//    List<OperationDto> findByStatusAndMonthIdAndYearId(String status, Long monthId, Long yearId);

}
