package com.leanpay.loancalculator.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(generator = "pg-uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "number_of_months")
    private Integer numberOfMonths;

    @Column(name = "annual_interest_percent")
    private Integer annualInterestPercent;

}
