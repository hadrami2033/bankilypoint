package com.bankily.point.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bankily.point.entity.Year;
import com.bankily.point.payload.YearDto;
import com.bankily.point.repository.YearRepository;
import com.bankily.point.service.YearService;

@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Service
public class YearServiceImpl implements YearService {
	
	private ModelMapper modelMapper;
	
	private YearRepository yearRepository;
	
	public YearServiceImpl(ModelMapper modelMapper, YearRepository yearRepository) {
		super();
		this.modelMapper = modelMapper;
		this.yearRepository = yearRepository;
	}

	@Override
	public YearDto add(YearDto yearDto) {
        Year year = modelMapper.map(yearDto, Year.class);
        Year savedYear= yearRepository.save(year);
        return modelMapper.map(savedYear, YearDto.class);
	}
	
	@Override
	public YearDto findByYear(String year) {
        Year Year= yearRepository.findByYear(year);
        return modelMapper.map(Year, YearDto.class);
	}

	@Override
	public YearDto get(Long yearId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YearDto> getAll() {
        List<Year> years = yearRepository.findAll();
        return years.stream().map((year) -> modelMapper.map(year, YearDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public YearDto update(YearDto yearDto, Long yearId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long yearId) {
		// TODO Auto-generated method stub
		
	}

}
