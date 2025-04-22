package com.bankily.point.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankily.point.entity.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	Page<Operation> findByStatusAndTypeIdAndMonthIdAndYearId(String status, Long typeId, Long monthId, Long yearId, Pageable pageable);
    
	Page<Operation> findByStatusAndTypeIdAndMonthIdAndYearIdAndPhoneContainingOrderByIdDesc(String status, Long typeId, Long monthId, Long yearId, String search, Pageable pageable);

	Page<Operation> findByMonthIdAndYearId(Long monthId, Long yearId, Pageable pageable);

	Page<Operation> findByDateCreation(Date date, Pageable pageable);
	
	Page<Operation> findByStatusAndDateCreation(String status, Date date, Pageable pageable);
	
	Page<Operation> findAllByDateCreationBetweenAndPhoneIsNotOrderByIdDesc(Date startDate, Date andDate, String phone, Pageable pageable);

	Page<Operation> findByStatusAndMonthIdAndYearId(String status, Long monthId, Long yearId, Pageable pageable);

    List<Operation> findAllByDateCreationBetween(Date startDate, Date endDate);
    
    List<Operation> findByMonthIdAndYearId(Long monthId, Long yearId);
    
    List<Operation> findByStatusAndMonthIdAndYearId(String status, Long monthId, Long yearId);
    
    List<Operation> findByStatusAndTypeIdAndMonthIdAndYearId(String status, Long typeId, Long monthId, Long yearId);
    
    @Query("select sum(o.amount) from Operation o where o.year.id = ?1 and o.status = ?2")
    List<Double> getYearSolde(Long yearId, String status);
    
    @Query("select sum(o.amount) from Operation o where o.year.id = ?1 and o.status = ?2 and o.type.id = ?3")
    List<Double> getYearSoldeByType(Long yearId, String status, Long typeId);

}
