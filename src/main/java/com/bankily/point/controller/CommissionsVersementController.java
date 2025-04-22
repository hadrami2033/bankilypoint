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

import com.bankily.point.payload.CommissionsVersementDto;
import com.bankily.point.service.CommissionsVersementService;
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:2024"})
@RestController
@RequestMapping("/api/point/commissionsversement")
public class CommissionsVersementController {
	
	private CommissionsVersementService commissionsVersementService;

	public CommissionsVersementController(CommissionsVersementService commissionsVersementService) {
		super();
		this.commissionsVersementService = commissionsVersementService;
	}


    @GetMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<CommissionsVersementDto>> getAll(){
        return ResponseEntity.ok(commissionsVersementService.getAll());
    }
    
    @PostMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CommissionsVersementDto> add(@RequestBody CommissionsVersementDto commissionDto){
    	CommissionsVersementDto saved = commissionsVersementService.add(commissionDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<CommissionsVersementDto> get(@PathVariable("id") Long id){
    	CommissionsVersementDto commissionsVersementDto = commissionsVersementService.get(id);
         return ResponseEntity.ok(commissionsVersementDto);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<CommissionsVersementDto> update(@RequestBody CommissionsVersementDto commissionDto,
                                                      @PathVariable("id") Long id){
        return ResponseEntity.ok(commissionsVersementService.update(commissionDto, id));
    }
	
}
