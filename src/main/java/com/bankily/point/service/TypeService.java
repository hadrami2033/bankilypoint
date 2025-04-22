package com.bankily.point.service;

import java.util.List;

import com.bankily.point.payload.TypeDto;

public interface TypeService {
	TypeDto add(TypeDto typeDto);

	TypeDto get(Long typeId);

    List<TypeDto> getAll();

    TypeDto update(TypeDto typeDto, Long typeId);

    void delete(Long typeId);
}
