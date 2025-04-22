package com.bankily.point.service;

import java.util.List;

import com.bankily.point.payload.YearDto;

public interface YearService {
	YearDto add(YearDto yearDto);

	YearDto get(Long yearId);
	
	YearDto findByYear(String year);

    List<YearDto> getAll();

    YearDto update(YearDto yearDto, Long yearId);

    void delete(Long yearId);
}
