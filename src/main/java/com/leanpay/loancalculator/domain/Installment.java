package com.leanpay.loancalculator.domain;

import com.leanpay.loancalculator.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "installment")
public class Installment extends BaseEntity {

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "number_of_month")
    private Integer numberOfMonth;

    @JoinColumn(name = "loan_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Loan loan;

}
