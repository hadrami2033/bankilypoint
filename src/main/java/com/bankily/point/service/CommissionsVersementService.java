package com.bankily.point.service;

import java.util.List;

import com.bankily.point.payload.CommissionsVersementDto;

public interface CommissionsVersementService {
	CommissionsVersementDto add(CommissionsVersementDto commissionsVersementDto);

	CommissionsVersementDto get(Long id);

    List<CommissionsVersementDto> getAll();

    CommissionsVersementDto update(CommissionsVersementDto commissionsVersementDto, Long id);

}
