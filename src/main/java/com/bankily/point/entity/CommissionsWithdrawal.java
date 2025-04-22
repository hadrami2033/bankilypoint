package com.bankily.point.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
        name = "commissionswithdrawal" //, uniqueConstraints = {@UniqueConstraint(columnNames = {"trsid"})}
)
public class CommissionsWithdrawal {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    
    @Column(name = "min", nullable = false)
    private float min;
    
    @Column(name = "max", nullable = false)
    private float max;
    
    @Column(name = "commission", nullable = false)
    private float commission;

	public CommissionsWithdrawal(float min, float max, float commission) {
		super();
		this.min = min;
		this.max = max;
		this.commission = commission;
	}
    
    
}
