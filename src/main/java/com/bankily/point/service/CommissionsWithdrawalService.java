package com.bankily.point.service;

import java.util.List;

import com.bankily.point.payload.CommissionsWithdrawalDto;

public interface CommissionsWithdrawalService {
	CommissionsWithdrawalDto add(CommissionsWithdrawalDto commissionsWithdrawalDto);

	CommissionsWithdrawalDto get(Long id);

    List<CommissionsWithdrawalDto> getAll();

    CommissionsWithdrawalDto update(CommissionsWithdrawalDto commissionsWithdrawalDto, Long id);
}
