package com.leanpay.loancalculator.domain;

import com.leanpay.loancalculator.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "installment")
public class Installment extends BaseEntity {

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "number_of_month")
    private Integer numberOfMonth;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

}
