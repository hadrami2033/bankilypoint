package com.bankily.point.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankily.point.payload.TypeDto;
import com.bankily.point.service.TypeService;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:2024"})
@RestController
@RequestMapping("/api/point/types")
public class TypeController {
	private TypeService typeService;

	public TypeController(TypeService typeService) {
		this.typeService = typeService;
	}
	
    // Build Add Category REST API
    @PostMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<TypeDto> addType(@RequestBody TypeDto typeDto){
        TypeDto savedType = typeService.add(typeDto);
        return new ResponseEntity<>(savedType, HttpStatus.CREATED);
    }
    
    // Build Get Category REST API
    @GetMapping("{id}")
    public ResponseEntity<TypeDto> getTypes(@PathVariable("id") Long typeId){
         TypeDto typeDto = typeService.get(typeId);
         return ResponseEntity.ok(typeDto);
    }

    // Build Get All Categories REST API
    @GetMapping
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<TypeDto>> getTypes(){
        return ResponseEntity.ok(typeService.getAll());
    }
}
