package com.leanpay.loancalculator.domain;

import com.leanpay.loancalculator.domain.base.BaseEntity;
import com.leanpay.loancalculator.domain.enumeration.PaymentFrequency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
public class Loan extends BaseEntity {

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "interest_rate")
    private Float interestRate;

    @Column(name = "number_of_months")
    private Integer numberOfMonths;

    @Column(name = "total_interest_amount")
    private BigDecimal totalInterestAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_frequency")
    private PaymentFrequency paymentFrequency = PaymentFrequency.MONTHLY;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Installment> installmentList = new ArrayList<>();

}
