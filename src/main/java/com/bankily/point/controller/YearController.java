package com.bankily.point.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankily.point.payload.YearDto;
import com.bankily.point.service.YearService;

//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
//@CrossOrigin(origins= {"http://localhost:3000"})
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:2024" })

@RestController
@RequestMapping("/api/point/years")
public class YearController {
	private YearService yearService;

	public YearController(YearService yearService) {
		super();
		this.yearService = yearService;
	}
	
	//@CrossOrigin(origins="http://localhost:3000")
    @GetMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<YearDto>> getYears(){
        return ResponseEntity.ok(yearService.getAll());
    }
    
    @GetMapping(value = "/byyear/{year}")
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<YearDto> getYear(@PathVariable(name = "year") String year){
        return ResponseEntity.ok(yearService.findByYear(year));
    }

}
