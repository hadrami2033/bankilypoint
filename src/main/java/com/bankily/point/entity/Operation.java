package com.bankily.point.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "operations" //, uniqueConstraints = {@UniqueConstraint(columnNames = {"trsid"})}
)
public class Operation {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @Column(name = "trsid", nullable = false)
    private String trsId;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "amount", nullable = false)
    private float amount;
    
    @Column(name = "commission", nullable = false)
    private float commission;
    
    @Column(name = "dateCreation", nullable = false)
    private Date dateCreation;
    
    @Column(name = "updatedAt", nullable = true)
    private Date updatedAt;

    @Column(name = "description", nullable = true)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_id")
    private Month month;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id")
    private Year year;

}
