package com.bankily.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankily.point.entity.Month;

public interface MonthRepository extends JpaRepository<Month, Long> {
	Month findByCode(int code);
}
