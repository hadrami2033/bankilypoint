package com.bankily.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankily.point.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Long>{
	Type findByLabel(String type);
}
