package com.bankily.point.service;

import java.util.List;

import com.bankily.point.payload.MonthDto;

public interface MonthService {
	MonthDto add(MonthDto monthDto);

	MonthDto get(Long monthId);
	
	MonthDto findByCode(int code);

    List<MonthDto> getAll();

    MonthDto update(MonthDto monthDto, Long monthId);

    void delete(Long monthId);
    
    
}
