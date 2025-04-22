package com.bankily.point.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankily.point.payload.MonthDto;
import com.bankily.point.service.MonthService;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:2024"})
@RestController
@RequestMapping("/api/point/months")
public class MonthController {
	private MonthService monthService;

	public MonthController(MonthService monthService) {
		super();
		this.monthService = monthService;
	}

    @PostMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<MonthDto> addMonth(@RequestBody MonthDto monthDto){
        MonthDto savedMonth= monthService.add(monthDto);
        return new ResponseEntity<>(savedMonth, HttpStatus.CREATED);
    }
    
    @GetMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<MonthDto>> getMonths(){
        return ResponseEntity.ok(monthService.getAll());
    }
    
    @GetMapping(value = "/bycode/{code}")
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<MonthDto> getMonthByCode(@PathVariable(name = "code") int code){
        return ResponseEntity.ok(monthService.findByCode(code));
    }
}
