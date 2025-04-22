package com.bankily.point.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "months" , uniqueConstraints = {@UniqueConstraint(columnNames = {"month", "code"})}
)
public class Month {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    
    @Column(name = "code", nullable = false)
    private Integer code;
    
    @Column(name = "month", nullable = false)
    private String month;

	public Month(Integer code, String month) {
		super();
		this.code = code;
		this.month = month;
	}
    
    
}
