package com.bankily.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bankily.point.entity.Year;

@CrossOrigin(origins="*")
public interface YearRepository extends JpaRepository<Year, Long> {
	Year findByYear(String year);
}
