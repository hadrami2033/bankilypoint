package com.bankily.point.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bankily.point.entity.CommissionsWithdrawal;
import com.bankily.point.exception.ResourceNotFoundException;
import com.bankily.point.payload.CommissionsWithdrawalDto;
import com.bankily.point.repository.CommissionsWithdrawalRepository;
import com.bankily.point.service.CommissionsWithdrawalService;

@Service
public class CommissionsWithdrawalServiceImp implements CommissionsWithdrawalService {

	private ModelMapper modelMapper;
	
	private CommissionsWithdrawalRepository commissionsWithdrawalRepository;
	
	
	public CommissionsWithdrawalServiceImp(ModelMapper modelMapper,
			CommissionsWithdrawalRepository commissionsWithdrawalRepository) {
		super();
		this.modelMapper = modelMapper;
		this.commissionsWithdrawalRepository = commissionsWithdrawalRepository;
	}

	@Override
	public CommissionsWithdrawalDto add(CommissionsWithdrawalDto commissionsWithdrawalDto) {
		CommissionsWithdrawal commissionsWithdrawal = modelMapper.map(commissionsWithdrawalDto, CommissionsWithdrawal.class);
		CommissionsWithdrawal savedCommissionsWithdrawal = commissionsWithdrawalRepository.save(commissionsWithdrawal);
        return modelMapper.map(savedCommissionsWithdrawal, CommissionsWithdrawalDto.class);
	}

	@Override
	public CommissionsWithdrawalDto get(Long id) {
		CommissionsWithdrawal commissionsWithdrawal = commissionsWithdrawalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commission", "id", id));
        return modelMapper.map(commissionsWithdrawal, CommissionsWithdrawalDto.class);
	
	}

	@Override
	public List<CommissionsWithdrawalDto> getAll() {
        List<CommissionsWithdrawal> commissionsWithdrawals = commissionsWithdrawalRepository.findAll();
        return commissionsWithdrawals.stream().map((c) -> modelMapper.map(c, CommissionsWithdrawalDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public CommissionsWithdrawalDto update(CommissionsWithdrawalDto commissionsWithdrawalDto, Long id) {
		CommissionsWithdrawal commissionsWithdrawal = commissionsWithdrawalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commission", "id", id));

		commissionsWithdrawal.setMax(commissionsWithdrawalDto.getMax());
		commissionsWithdrawal.setMin(commissionsWithdrawalDto.getMin());
		commissionsWithdrawal.setCommission(commissionsWithdrawalDto.getCommission());

		CommissionsWithdrawal updatedCommissionsWithdrawal = commissionsWithdrawalRepository.save(commissionsWithdrawal);

        return modelMapper.map(updatedCommissionsWithdrawal, CommissionsWithdrawalDto.class);
	
	}

}
