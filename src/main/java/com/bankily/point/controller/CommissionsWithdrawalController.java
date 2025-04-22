package com.bankily.point.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankily.point.payload.CommissionsWithdrawalDto;
import com.bankily.point.service.CommissionsWithdrawalService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:2024"})
@RestController
@RequestMapping("/api/point/commissionswithdrawal")
public class CommissionsWithdrawalController {

	private CommissionsWithdrawalService commissionsWithdrawalService;

	public CommissionsWithdrawalController(CommissionsWithdrawalService commissionsWithdrawalService) {
		super();
		this.commissionsWithdrawalService = commissionsWithdrawalService;
	}
	
    @GetMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<CommissionsWithdrawalDto>> getAll(){
        return ResponseEntity.ok(commissionsWithdrawalService.getAll());
    }
    
    @PostMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CommissionsWithdrawalDto> add(@RequestBody CommissionsWithdrawalDto commissionsWithdrawalDto){
    	CommissionsWithdrawalDto saved = commissionsWithdrawalService.add(commissionsWithdrawalDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<CommissionsWithdrawalDto> get(@PathVariable("id") Long id){
    	CommissionsWithdrawalDto commissionsWithdrawalDto = commissionsWithdrawalService.get(id);
         return ResponseEntity.ok(commissionsWithdrawalDto);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<CommissionsWithdrawalDto> update(@RequestBody CommissionsWithdrawalDto commissionsWithdrawalDto,
                                                      @PathVariable("id") Long id){
        return ResponseEntity.ok(commissionsWithdrawalService.update(commissionsWithdrawalDto, id));
    }
	

}
