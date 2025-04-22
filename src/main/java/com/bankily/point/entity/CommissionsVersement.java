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
        name = "commissionsversement" //, uniqueConstraints = {@UniqueConstraint(columnNames = {"trsid"})}
)
public class CommissionsVersement {
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

	public CommissionsVersement(float min, float max, float commission) {
		super();
		this.min = min;
		this.max = max;
		this.commission = commission;
	}
    
    
}
