package com.bankily.point.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankily.point.payload.OperationDto;
import com.bankily.point.payload.OperationResponse;
import com.bankily.point.payload.StatisticsResponse;
import com.bankily.point.payload.YearSolde;
import com.bankily.point.service.OperationService;
import com.bankily.point.utils.AppConstants;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:2024"})
@RestController
@RequestMapping()
public class OperationController {
	
	private OperationService operationService;

	public OperationController(OperationService operationService) {
		this.operationService = operationService;
	}
	
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/point/operations")
    public ResponseEntity<OperationDto> createOperation(@Valid @RequestBody OperationDto operationDto){
        return new ResponseEntity<>(operationService.add(operationDto), HttpStatus.CREATED);
    }
    
    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/api/point/operations")
    public OperationResponse getAllOperations(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return operationService.getAll(pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/{id}")
    public ResponseEntity<OperationDto> getById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(operationService.get(id));
    }
    
    @GetMapping(value = "/api/point/operations/yearsolde/{id}")
    public ResponseEntity<YearSolde> getYearSolde(@PathVariable(name = "id" ) long yearId){
        return ResponseEntity.ok(operationService.getYearSolde(yearId, "P"));
    }
    
    @GetMapping(value = "/api/point/operations/yearsolde/bytype")
    public ResponseEntity<YearSolde> getYearSoldeByType(
    		@RequestParam(value = "yearId", required = true) long yearId,
    		@RequestParam(value = "typeId", required = true) long typeId ){
        return ResponseEntity.ok(operationService.getYearSoldeByType(yearId, "P", typeId));
    }
    
    @GetMapping(value = "/api/point/operations/bytype")
    public OperationResponse getByType(
    		 @RequestParam(name = "typeId") long typeId,
    		 @RequestParam(value = "status", required = true) String status,
    		 @RequestParam(value = "month", required = true) long month,
    		 @RequestParam(value = "year", required = true) long year,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.getByType(status,typeId,month,year, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/entrees")
    public OperationResponse getEntrees(
    		 @RequestParam(value = "status", required = true) String status,
    		 @RequestParam(value = "month", required = true) long month,
    		 @RequestParam(value = "year", required = true) long year,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.Entrees(status,month,year, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/wallets")
    public OperationResponse getWallets(
    		 @RequestParam(value = "status", required = true) String status,
    		 @RequestParam(value = "month", required = true) long month,
    		 @RequestParam(value = "year", required = true) long year,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.Wallets(status,month,year, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/versements")
    public OperationResponse getVersements(
    		 @RequestParam(value = "status", required = true) String status,
    		 @RequestParam(value = "month", required = true) long month,
    		 @RequestParam(value = "year", required = true) long year,
    		 @RequestParam(value = "search", required = true) String search,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.Versements(status,month,year,search, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/withdrawals")
    public OperationResponse getWithdrawal(
    		 @RequestParam(value = "status", required = true) String status,
    		 @RequestParam(value = "month", required = true) long month,
    		 @RequestParam(value = "year", required = true) long year,
    		 @RequestParam(value = "search", required = true) String search,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.Withdrawals(status, month, year, search, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/withdrawals2")
    public OperationResponse getWithdrawal2(
    		 @RequestParam(value = "status", required = true) String status,
    		 @RequestParam(value = "month", required = true) long month,
    		 @RequestParam(value = "year", required = true) long year,
    		 @RequestParam(value = "search", required = true) String search,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.Withdrawals2(status, month, year, search, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/bymonthyear")
    public OperationResponse findByMonthIdAndYearId(
    		 @RequestParam(value = "month", required = true) long month,
    		 @RequestParam(value = "year", required = true) long year,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.findByMonthIdAndYearId(month, year, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/bydate")
    public OperationResponse findByDateCreation(
    		 @RequestParam(value = "date", required = true) Date date,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.findByDateCreation(date, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/byinterval")
    public OperationResponse findByInterval(
    		 @RequestParam(value = "startDate", required = true) Date startDate,
    		 @RequestParam(value = "endDate", required = true) Date endDate,
    		 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    
    ){
        return operationService.findAllByDateBetween(startDate, endDate, pageNo, pageSize, sortBy, sortDir);
    }
    
    @GetMapping(value = "/api/point/operations/yearstatistics")
    public List<StatisticsResponse> yearStatistics(
    		 @RequestParam(value = "yearId", required = true) Long yearId
    ){
        return operationService.yearStatistics(yearId);
    }
    
    // update post by id rest api
    //@PreAuthorize("hasRole('USER')")
    @PutMapping("/api/point/operations/{id}")
    public ResponseEntity<OperationDto> updateOpreration(@Valid @RequestBody OperationDto operationDto, @PathVariable(name = "id") long id){

       OperationDto operationResponse = operationService.update(operationDto, id);

       return new ResponseEntity<>(operationResponse, HttpStatus.OK);
    }
    
    //@PreAuthorize("hasRole('USER')")
    @DeleteMapping("/api/point/operations/{id}")
    public ResponseEntity<String> deleteOpreration(@PathVariable(name = "id") long id){
       operationService.delete(id);
       return new ResponseEntity<>("Opperation deleted successfully.", HttpStatus.OK);
    }
}
