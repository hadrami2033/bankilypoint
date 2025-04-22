package com.bankily.point.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bankily.point.entity.Month;
import com.bankily.point.payload.MonthDto;
import com.bankily.point.repository.MonthRepository;
import com.bankily.point.service.MonthService;

@Service
public class MonthServiceImpl implements MonthService  {

	private ModelMapper modelMapper;
	
	private MonthRepository monthRepository;
	
	public MonthServiceImpl(ModelMapper modelMapper, MonthRepository monthRepository) {
		super();
		this.modelMapper = modelMapper;
		this.monthRepository = monthRepository;
	}

	@Override
	public MonthDto add(MonthDto monthDto) {
        Month month = modelMapper.map(monthDto, Month.class);
        Month savedMonth= monthRepository.save(month);
        return modelMapper.map(savedMonth, MonthDto.class);
	}
	
	@Override
	public MonthDto findByCode(int code) {
        Month month= monthRepository.findByCode(code);
        return modelMapper.map(month, MonthDto.class);
	}

	@Override
	public MonthDto get(Long monthId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MonthDto> getAll() {
        List<Month> months = monthRepository.findAll();
        return months.stream().map((month) -> modelMapper.map(month, MonthDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public MonthDto update(MonthDto monthDto, Long monthId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long monthId) {
		// TODO Auto-generated method stub
	}

}
