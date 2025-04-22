package com.bankily.point.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bankily.point.entity.Type;
import com.bankily.point.payload.TypeDto;
import com.bankily.point.repository.TypeRepository;
import com.bankily.point.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	private ModelMapper modelMapper;
	
	private TypeRepository typeRepository;
	
	
	public TypeServiceImpl(ModelMapper modelMapper, TypeRepository typeRepository) {
		this.modelMapper = modelMapper;
		this.typeRepository = typeRepository;
	}

	@Override
	public TypeDto add(TypeDto typeDto) {
        Type type = modelMapper.map(typeDto, Type.class);
        Type savedType = typeRepository.save(type);
        return modelMapper.map(savedType, TypeDto.class);
	}

	@Override
	public TypeDto get(Long typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeDto> getAll() {
        List<Type> types = typeRepository.findAll();

        return types.stream().map((type) -> modelMapper.map(type, TypeDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public TypeDto update(TypeDto typeDto, Long typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long typeId) {
		// TODO Auto-generated method stub
		
	}

}
