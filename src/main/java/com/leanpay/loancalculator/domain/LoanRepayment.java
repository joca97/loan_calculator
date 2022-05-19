package com.leanpay.loancalculator.domain;

import com.leanpay.loancalculator.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_repayment")
public class LoanRepayment extends BaseEntity {

    @Column(name = "payment_amount")
    private Float paymentAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Loan loan;

}
