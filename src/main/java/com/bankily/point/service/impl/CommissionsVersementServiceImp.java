package com.bankily.point.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bankily.point.entity.CommissionsVersement;
import com.bankily.point.exception.ResourceNotFoundException;
import com.bankily.point.payload.CommissionsVersementDto;
import com.bankily.point.repository.CommissionsVersementRepository;
import com.bankily.point.service.CommissionsVersementService;

@Service
public class CommissionsVersementServiceImp implements CommissionsVersementService {

	private ModelMapper modelMapper;
	
	private CommissionsVersementRepository commissionsVersementRepository;
	
	
	public CommissionsVersementServiceImp(ModelMapper modelMapper,
			CommissionsVersementRepository commissionsVersementRepository) {
		super();
		this.modelMapper = modelMapper;
		this.commissionsVersementRepository = commissionsVersementRepository;
	}

	@Override
	public CommissionsVersementDto add(CommissionsVersementDto commissionsVersementDto) {
        CommissionsVersement commissionVer = modelMapper.map(commissionsVersementDto, CommissionsVersement.class);
        CommissionsVersement savedCommissionVer = commissionsVersementRepository.save(commissionVer);
        return modelMapper.map(savedCommissionVer, CommissionsVersementDto.class);
	}

	@Override
	public CommissionsVersementDto get(Long id) {
		CommissionsVersement commissionsVersement = commissionsVersementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commission", "id", id));
        return modelMapper.map(commissionsVersement, CommissionsVersementDto.class);
	}

	@Override
	public List<CommissionsVersementDto> getAll() {
        List<CommissionsVersement> commissionsVersements = commissionsVersementRepository.findAll();
        return commissionsVersements.stream().map((c) -> modelMapper.map(c, CommissionsVersementDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public CommissionsVersementDto update(CommissionsVersementDto commissionsVersementDto, Long id) {
		CommissionsVersement commissionsVersement = commissionsVersementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commission", "id", id));

		commissionsVersement.setMax(commissionsVersementDto.getMax());
		commissionsVersement.setMin(commissionsVersementDto.getMin());
		commissionsVersement.setCommission(commissionsVersementDto.getCommission());

		CommissionsVersement updatedCommissionsVersement = commissionsVersementRepository.save(commissionsVersement);

        return modelMapper.map(updatedCommissionsVersement, CommissionsVersementDto.class);
	}

}
