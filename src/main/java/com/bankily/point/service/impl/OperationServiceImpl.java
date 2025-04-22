package com.bankily.point.service.impl;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bankily.point.entity.Month;
import com.bankily.point.entity.Operation;
import com.bankily.point.entity.Type;
import com.bankily.point.entity.Year;
import com.bankily.point.exception.ResourceNotFoundException;
import com.bankily.point.payload.OperationDto;
import com.bankily.point.payload.OperationResponse;
import com.bankily.point.payload.StatisticsResponse;
import com.bankily.point.payload.YearSolde;
import com.bankily.point.repository.MonthRepository;
import com.bankily.point.repository.OperationRepository;
import com.bankily.point.repository.TypeRepository;
import com.bankily.point.repository.YearRepository;
import com.bankily.point.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	private ModelMapper mapper;

    private OperationRepository operationRepository;
    
    private TypeRepository typeRepository;
    
    private MonthRepository monthRepository;

    private YearRepository yearRepository;
    
    public OperationServiceImpl(ModelMapper mapper, OperationRepository operationRepository,
			TypeRepository typeRepository, MonthRepository monthRepository, YearRepository yearRepository) {
		super();
		this.mapper = mapper;
		this.mapper.getConfiguration().setAmbiguityIgnored(true);
		this.operationRepository = operationRepository;
		this.typeRepository = typeRepository;
		this.monthRepository = monthRepository;
		this.yearRepository = yearRepository;
	}

	// convert Entity into DTO
    private OperationDto mapToDTO(Operation operation){
    	OperationDto operationDto = mapper.map(operation, OperationDto.class);
        return operationDto;
    }

    // convert DTO to entity
    private Operation mapToEntity(OperationDto operationDto){
    	Operation operation = mapper.map(operationDto, Operation.class);
    	
        return operation;
    }
    
	@Override
	public OperationDto add(OperationDto operationDto) {
        Type type = typeRepository.findById(operationDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", operationDto.getTypeId()));
        Month month = monthRepository.findById(operationDto.getMonthId())
                .orElseThrow(() -> new ResourceNotFoundException("Month", "id", operationDto.getMonthId()));
        Year year = yearRepository.findById(operationDto.getYearId())
                .orElseThrow(() -> new ResourceNotFoundException("Year", "id", operationDto.getYearId()));

        // convert DTO to entity
        Operation operation = mapToEntity(operationDto);
        operation.setType(type);
        operation.setYear(year);
        operation.setMonth(month);

        Operation newOperation = operationRepository.save(operation);

        // convert entity to DTO
        OperationDto operationResponse = mapToDTO(newOperation);
        return operationResponse;
	}

	@Override
	public OperationDto get(Long operationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Operation> operations = operationRepository.findAll(pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;
	}

	@Override
	public OperationDto update(OperationDto operationDto, Long operationId) {
		Operation o = operationRepository.findById(operationId)
                .orElseThrow(() -> new ResourceNotFoundException("opperation", "id", operationId));

        o.setTrsId(operationDto.getTrsId());
        o.setPhone(operationDto.getPhone());
        o.setStatus(operationDto.getStatus());
        o.setAmount(operationDto.getAmount());
        o.setCommission(operationDto.getCommission());
        o.setUpdatedAt(operationDto.getUpdatedAt());
        o.setDescription(operationDto.getDescription());

        Operation updatedOperation = operationRepository.save(o);

        return mapper.map(updatedOperation, OperationDto.class);
	}

	@Override
	public void delete(Long operationId) {
        Operation opp = operationRepository.findById(operationId).orElseThrow(() -> new ResourceNotFoundException("Operation", "id", operationId));
        operationRepository.delete(opp);	
	}


	@Override
	public OperationResponse getByType(String status, Long typeId, Long monthId, Long yearId, int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId(status, typeId, monthId, yearId,  pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}
	
	@Override
	public OperationResponse findByMonthIdAndYearId(Long monthId, Long yearId,int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByMonthIdAndYearId(monthId, yearId, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}
	
	@Override
	public OperationResponse findByDateCreation(Date date,int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByDateCreation(date, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}
	
	@Override
	public OperationResponse findByStatusAndDateCreation(String status,Date date,int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByStatusAndDateCreation(status, date, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}
	
	@Override
	public OperationResponse findAllByDateBetween(Date startDate, Date endDate,int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findAllByDateCreationBetweenAndPhoneIsNotOrderByIdDesc(startDate, endDate, "00000000", pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}
	
	@Override
	public OperationResponse findByStatusAndMonthIdAndYearId(String status, Long monthId, Long yearId,int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByStatusAndMonthIdAndYearId(status, monthId, yearId, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}
	
	@Override
	public OperationResponse Versements(String status, Long monthId, Long yearId, String search, int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
		Type versement = typeRepository.findByLabel("versement");

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearIdAndPhoneContainingOrderByIdDesc(status, versement.getId(), monthId, yearId, search, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}
	
	
	@Override
	public OperationResponse Entrees(String status, Long monthId, Long yearId, int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
		Type entree = typeRepository.findByLabel("entree");

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId(status, entree.getId(), monthId, yearId, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}

	@Override
	public OperationResponse Wallets(String status, Long monthId, Long yearId, int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
		Type wallet = typeRepository.findByLabel("wallets");

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId(status, wallet.getId(), monthId, yearId, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}

	
	@Override
	public OperationResponse Withdrawals(String status, Long monthId, Long yearId, String search, int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
		Type withdrawal = typeRepository.findByLabel("withdrawal");

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearIdAndPhoneContainingOrderByIdDesc(status, withdrawal.getId(), monthId, yearId, search, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}
	
	@Override
	public OperationResponse Withdrawals2(String status, Long monthId, Long yearId, String search, int pageNo, int pageSize, String sortBy, String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
		Type withdrawal = typeRepository.findByLabel("withdrawal2");

        //Page<Operation> operations = operationRepository.findAll(pageable);
        Page<Operation> operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearIdAndPhoneContainingOrderByIdDesc(status, withdrawal.getId(), monthId, yearId, search, pageable);

        // get content for page object
        List<Operation> listOfOperations = operations.getContent();

        List<OperationDto> content= listOfOperations.stream().map(operation -> mapToDTO(operation)).collect(Collectors.toList());

        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setContent(content);
        operationResponse.setPageNo(operations.getNumber());
        operationResponse.setPageSize(operations.getSize());
        operationResponse.setTotalElements(operations.getTotalElements());
        operationResponse.setTotalPages(operations.getTotalPages());
        operationResponse.setLast(operations.isLast());

        return operationResponse;

	}


	@Override
	public YearSolde getYearSolde(Long yearId, String status) {
		List<Double> soldeList = operationRepository.getYearSolde(yearId, status);
		double solde = (soldeList.size() > 0 && soldeList.get(0) != null ) ? soldeList.get(0) : 0;
		YearSolde yearSolde = new YearSolde(solde);
		return yearSolde;
	}
	
	@Override
	public YearSolde getYearSoldeByType(Long yearId, String status, Long typeId) {
		List<Double> soldeList = operationRepository.getYearSoldeByType(yearId, status, typeId);
		double solde = (soldeList.size() > 0 && soldeList.get(0) != null ) ? soldeList.get(0) : 0;
		YearSolde yearSolde = new YearSolde(solde);
		return yearSolde;
	}
	
	@Override
	public List<StatisticsResponse> yearStatistics(Long yearId) {

		Month jan = monthRepository.findByCode(1);
		Month fev = monthRepository.findByCode(2);
		Month mar = monthRepository.findByCode(3);
		Month avr = monthRepository.findByCode(4);
		Month mai = monthRepository.findByCode(5);
		Month jui = monthRepository.findByCode(6);
		Month jul = monthRepository.findByCode(7);
		Month aou = monthRepository.findByCode(8);
		Month sep = monthRepository.findByCode(9);
		Month oct = monthRepository.findByCode(10);
		Month nov = monthRepository.findByCode(11);
		Month dec = monthRepository.findByCode(12);
		
		Type versement = typeRepository.findByLabel("versement");
		Type withdrawal = typeRepository.findByLabel("withdrawal");
		Type withdrawal2 = typeRepository.findByLabel("withdrawal2");


        List<Operation> jan_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", jan.getId(), yearId);
        List<Operation> fev_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", fev.getId(), yearId);
        List<Operation> mar_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", mar.getId(), yearId);
        List<Operation> avr_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", avr.getId(), yearId);
        List<Operation> mai_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", mai.getId(), yearId);
        List<Operation> jui_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", jui.getId(), yearId);
        List<Operation> jul_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", jul.getId(), yearId);
        List<Operation> aou_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", aou.getId(), yearId);
        List<Operation> sep_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", sep.getId(), yearId);
        List<Operation> oct_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", oct.getId(), yearId);
        List<Operation> nov_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", nov.getId(), yearId);
        List<Operation> dec_valid_operations = operationRepository.findByStatusAndMonthIdAndYearId("P", dec.getId(), yearId);

        List<Operation> jan_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", jan.getId(), yearId);
        List<Operation> fev_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", fev.getId(), yearId);
        List<Operation> mar_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", mar.getId(), yearId);
        List<Operation> avr_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", avr.getId(), yearId);
        List<Operation> mai_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", mai.getId(), yearId);
        List<Operation> jui_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", jui.getId(), yearId);
        List<Operation> jul_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", jul.getId(), yearId);
        List<Operation> aou_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", aou.getId(), yearId);
        List<Operation> sep_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", sep.getId(), yearId);
        List<Operation> oct_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", oct.getId(), yearId);
        List<Operation> nov_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", nov.getId(), yearId);
        List<Operation> dec_invalid_operations = operationRepository.findByStatusAndMonthIdAndYearId("R", dec.getId(), yearId);

        List<Operation> jan_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), jan.getId(), yearId);
        List<Operation> fev_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), fev.getId(), yearId);
        List<Operation> mar_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), mar.getId(), yearId);
        List<Operation> avr_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), avr.getId(), yearId);
        List<Operation> mai_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), mai.getId(), yearId);
        List<Operation> jui_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), jui.getId(), yearId);
        List<Operation> jul_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), jul.getId(), yearId);
        List<Operation> aou_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), aou.getId(), yearId);
        List<Operation> sep_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), sep.getId(), yearId);
        List<Operation> oct_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), oct.getId(), yearId);
        List<Operation> nov_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), nov.getId(), yearId);
        List<Operation> dec_ver_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P", versement.getId(), dec.getId(), yearId);

        
        List<Operation> jan_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), jan.getId(), yearId);
        List<Operation> fev_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), fev.getId(), yearId);
        List<Operation> mar_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), mar.getId(), yearId);
        List<Operation> avr_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), avr.getId(), yearId);
        List<Operation> mai_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), mai.getId(), yearId);
        List<Operation> jui_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), jui.getId(), yearId);
        List<Operation> jul_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), jul.getId(), yearId);
        List<Operation> aou_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), aou.getId(), yearId);
        List<Operation> sep_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), sep.getId(), yearId);
        List<Operation> oct_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), oct.getId(), yearId);
        List<Operation> nov_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), nov.getId(), yearId);
        List<Operation> dec_ret_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal.getId(), dec.getId(), yearId);

        List<Operation> jan_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), jan.getId(), yearId);
        List<Operation> fev_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), fev.getId(), yearId);
        List<Operation> mar_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), mar.getId(), yearId);
        List<Operation> avr_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), avr.getId(), yearId);
        List<Operation> mai_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), mai.getId(), yearId);
        List<Operation> jui_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), jui.getId(), yearId);
        List<Operation> jul_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), jul.getId(), yearId);
        List<Operation> aou_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), aou.getId(), yearId);
        List<Operation> sep_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), sep.getId(), yearId);
        List<Operation> oct_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), oct.getId(), yearId);
        List<Operation> nov_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), nov.getId(), yearId);
        List<Operation> dec_ret2_operations = operationRepository.findByStatusAndTypeIdAndMonthIdAndYearId("P",withdrawal2.getId(), dec.getId(), yearId);

        double jan_valid_amoun = jan_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double fev_valid_amoun = fev_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mar_valid_amoun = mar_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double avr_valid_amoun = avr_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mai_valid_amoun = mai_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jui_valid_amoun = jui_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jul_valid_amoun = jul_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double aou_valid_amoun = aou_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double sep_valid_amoun = sep_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double oct_valid_amoun = oct_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double nov_valid_amoun = nov_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double dec_valid_amoun = dec_valid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        Double[] amounts_array = {
        		jan_valid_amoun, fev_valid_amoun, mar_valid_amoun, avr_valid_amoun, mai_valid_amoun, 
        		jui_valid_amoun, jul_valid_amoun, aou_valid_amoun, sep_valid_amoun, oct_valid_amoun, nov_valid_amoun, dec_valid_amoun};
        double max_valid_amount = Collections.max(Arrays.asList(amounts_array));
        
        double jan_invalid_amoun = jan_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double fev_invalid_amoun = fev_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mar_invalid_amoun = mar_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double avr_invalid_amoun = avr_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mai_invalid_amoun = mai_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jui_invalid_amoun = jui_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jul_invalid_amoun = jul_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double aou_invalid_amoun = aou_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double sep_invalid_amoun = sep_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
		double oct_invalid_amoun = oct_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double nov_invalid_amoun = nov_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double dec_invalid_amoun = dec_invalid_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        
        double jan_ret_amoun = jan_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double fev_ret_amoun = fev_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mar_ret_amoun = mar_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double avr_ret_amoun = avr_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mai_ret_amoun = mai_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jui_ret_amoun = jui_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jul_ret_amoun = jul_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double aou_ret_amoun = aou_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double sep_ret_amoun = sep_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
		double oct_ret_amoun = oct_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double nov_ret_amoun = nov_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double dec_ret_amoun = dec_ret_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        
        double jan_ret2_amoun = jan_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double fev_ret2_amoun = fev_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mar_ret2_amoun = mar_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double avr_ret2_amoun = avr_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mai_ret2_amoun = mai_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jui_ret2_amoun = jui_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jul_ret2_amoun = jul_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double aou_ret2_amoun = aou_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double sep_ret2_amoun = sep_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
		double oct_ret2_amoun = oct_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double nov_ret2_amoun = nov_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double dec_ret2_amoun = dec_ret2_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        
        double jan_ver_amoun = jan_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double fev_ver_amoun = fev_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mar_ver_amoun = mar_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double avr_ver_amoun = avr_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double mai_ver_amoun = mai_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jui_ver_amoun = jui_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double jul_ver_amoun = jul_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double aou_ver_amoun = aou_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double sep_ver_amoun = sep_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
		double oct_ver_amoun = oct_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double nov_ver_amoun = nov_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        double dec_ver_amoun = dec_ver_operations.stream().mapToDouble(e -> e.getAmount()).sum();
        
        double jan_comm = jan_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double fev_comm = fev_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double mar_comm = mar_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double avr_comm = avr_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double mai_comm = mai_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double jui_comm = jui_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double jul_comm = jul_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double aou_comm = aou_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double oct_comm = oct_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double sep_comm = sep_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double nov_comm = nov_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        double dec_comm = dec_valid_operations.stream().mapToDouble(e -> e.getCommission()).sum();
        
        int jan_nbr_valid = jan_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int fev_nbr_valid = fev_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int mar_nbr_valid = mar_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int avr_nbr_valid = avr_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int mai_nbr_valid = mai_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int jui_nbr_valid = jui_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int jul_nbr_valid = jul_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int aou_nbr_valid = aou_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int sep_nbr_valid = sep_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int oct_nbr_valid = oct_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int nov_nbr_valid = nov_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        int dec_nbr_valid = dec_valid_operations.stream().filter(e -> !e.getType().getLabel().equals("wallets") && !e.getType().getLabel().equals("entree") ).toList().size();
        Integer[] nbrs_array = {
        		jan_nbr_valid, fev_nbr_valid, mar_nbr_valid, avr_nbr_valid, mai_nbr_valid, 
        		jui_nbr_valid, jul_nbr_valid, aou_nbr_valid, sep_nbr_valid, oct_nbr_valid, nov_nbr_valid, dec_nbr_valid};
        int max_valid_nbr = Collections.max(Arrays.asList(nbrs_array));
        
        int jan_nbr_invalid = jan_invalid_operations.size();
        int fev_nbr_invalid = fev_invalid_operations.size();
        int mar_nbr_invalid = mar_invalid_operations.size();
        int avr_nbr_invalid = avr_invalid_operations.size();
        int mai_nbr_invalid = mai_invalid_operations.size();
        int jui_nbr_invalid = jui_invalid_operations.size();
        int jul_nbr_invalid = jul_invalid_operations.size();
        int aou_nbr_invalid = aou_invalid_operations.size();
        int sep_nbr_invalid = sep_invalid_operations.size();
        int oct_nbr_invalid = oct_invalid_operations.size();
        int nov_nbr_invalid = nov_invalid_operations.size();
        int dec_nbr_invalid = dec_invalid_operations.size();
        
        
        int jan_ver = jan_ver_operations.size();
        int fev_ver = fev_ver_operations.size();
        int mar_ver = mar_ver_operations.size();
        int avr_ver = avr_ver_operations.size();
        int mai_ver = mai_ver_operations.size();
        int jui_ver = jui_ver_operations.size();
        int jul_ver = jul_ver_operations.size();
        int aou_ver = aou_ver_operations.size();
        int sep_ver = sep_ver_operations.size();
        int oct_ver = oct_ver_operations.size();
        int nov_ver = nov_ver_operations.size();
        int dec_ver = dec_ver_operations.size();

        int jan_ret = jan_ret_operations.size();
        int fev_ret = fev_ret_operations.size();
        int mar_ret = mar_ret_operations.size();
        int avr_ret = avr_ret_operations.size();
        int mai_ret = mai_ret_operations.size();
        int jui_ret = jui_ret_operations.size();
        int jul_ret = jul_ret_operations.size();
        int aou_ret = aou_ret_operations.size();
        int sep_ret = sep_ret_operations.size();
        int oct_ret = oct_ret_operations.size();
        int nov_ret = nov_ret_operations.size();
        int dec_ret = dec_ret_operations.size();

        int jan_ret2 = jan_ret2_operations.size();
        int fev_ret2 = fev_ret2_operations.size();
        int mar_ret2 = mar_ret2_operations.size();
        int avr_ret2 = avr_ret2_operations.size();
        int mai_ret2 = mai_ret2_operations.size();
        int jui_ret2 = jui_ret2_operations.size();
        int jul_ret2 = jul_ret2_operations.size();
        int aou_ret2 = aou_ret2_operations.size();
        int sep_ret2 = sep_ret2_operations.size();
        int oct_ret2 = oct_ret2_operations.size();
        int nov_ret2 = nov_ret2_operations.size();
        int dec_ret2 = dec_ret2_operations.size();
        
		StatisticsResponse jan_stat = new StatisticsResponse(jan_comm, jan_valid_amoun, jan_invalid_amoun, jan_nbr_valid, jan_nbr_invalid, 01,jan_ret_amoun+jan_ret2_amoun, jan_ver_amoun, jan_ret+jan_ret2, jan_ver);
        StatisticsResponse fev_stat = new StatisticsResponse(fev_comm, fev_valid_amoun, fev_invalid_amoun, fev_nbr_valid, fev_nbr_invalid, 02,fev_ret_amoun+fev_ret2_amoun, fev_ver_amoun, fev_ret+fev_ret2, fev_ver);
        StatisticsResponse mar_stat = new StatisticsResponse(mar_comm, mar_valid_amoun, mar_invalid_amoun, mar_nbr_valid, mar_nbr_invalid, 03,mar_ret_amoun+mar_ret2_amoun, mar_ver_amoun, mar_ret+mar_ret2, mar_ver);
        StatisticsResponse avr_stat = new StatisticsResponse(avr_comm, avr_valid_amoun, avr_invalid_amoun, avr_nbr_valid, avr_nbr_invalid, 04,avr_ret_amoun+avr_ret2_amoun, avr_ver_amoun, avr_ret+avr_ret2, avr_ver);
        StatisticsResponse mai_stat = new StatisticsResponse(mai_comm, mai_valid_amoun, mai_invalid_amoun, mai_nbr_valid, mai_nbr_invalid, 05,mai_ret_amoun+mai_ret2_amoun, mai_ver_amoun, mai_ret+mai_ret2, mai_ver);
        StatisticsResponse jui_stat = new StatisticsResponse(jui_comm, jui_valid_amoun, jui_invalid_amoun, jui_nbr_valid, jui_nbr_invalid, 06,jui_ret_amoun+jui_ret2_amoun, jui_ver_amoun, jui_ret+jui_ret2, jui_ver);
        StatisticsResponse jul_stat = new StatisticsResponse(jul_comm, jul_valid_amoun, jul_invalid_amoun, jul_nbr_valid, jul_nbr_invalid, 07,jul_ret_amoun+jul_ret2_amoun, jul_ver_amoun, jul_ret+jul_ret2, jul_ver);
        StatisticsResponse aou_stat = new StatisticsResponse(aou_comm, aou_valid_amoun, aou_invalid_amoun, aou_nbr_valid, aou_nbr_invalid, 8, aou_ret_amoun+aou_ret2_amoun, aou_ver_amoun, aou_ret+aou_ret2, aou_ver);
        StatisticsResponse sep_stat = new StatisticsResponse(sep_comm, sep_valid_amoun, sep_invalid_amoun, sep_nbr_valid, sep_nbr_invalid, 9, sep_ret_amoun+sep_ret2_amoun, sep_ver_amoun, sep_ret+sep_ret2, sep_ver);
        StatisticsResponse oct_stat = new StatisticsResponse(oct_comm, oct_valid_amoun, oct_invalid_amoun, oct_nbr_valid, oct_nbr_invalid, 10,oct_ret_amoun+oct_ret2_amoun, oct_ver_amoun, oct_ret+oct_ret2, oct_ver);
        StatisticsResponse nov_stat = new StatisticsResponse(nov_comm, nov_valid_amoun, nov_invalid_amoun, nov_nbr_valid, nov_nbr_invalid, 11,nov_ret_amoun+nov_ret2_amoun, nov_ver_amoun, nov_ret+nov_ret2, nov_ver);
        StatisticsResponse dec_stat = new StatisticsResponse(dec_comm, dec_valid_amoun, dec_invalid_amoun, dec_nbr_valid, dec_nbr_invalid, 12,dec_ret_amoun+dec_ret2_amoun, dec_ver_amoun, dec_ret+dec_ret2, dec_ver);
      
        StatisticsResponse max_val  = new StatisticsResponse(0, max_valid_amount, 0, max_valid_nbr, 0, 13,0,0,0,0);

        List<StatisticsResponse> statisticsResponse = List.of(jan_stat, fev_stat, mar_stat, avr_stat, mai_stat, jui_stat, jul_stat, aou_stat, sep_stat, oct_stat, nov_stat, dec_stat, max_val);

        return statisticsResponse;
	}
}
