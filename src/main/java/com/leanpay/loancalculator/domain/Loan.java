package com.leanpay.loancalculator.domain;

import com.leanpay.loancalculator.domain.base.BaseEntity;
import com.leanpay.loancalculator.domain.enumeration.PaymentFrequency;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan")
public class Loan extends BaseEntity {

    @Column(name = "amount")
    private Long amount;

    @Column(name = "interest_rate")
    private Integer interestRate;

    @Column(name = "number_of_months")
    private Integer numberOfMonths;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_frequency")
    private PaymentFrequency paymentFrequency;

    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanRepayment> loanRepaymentList = new ArrayList<>();

}
