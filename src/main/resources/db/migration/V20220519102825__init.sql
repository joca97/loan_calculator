create table if not exists loan
(
    id                uuid not null primary key,
    created_on        timestamp,
    updated_on        timestamp,
    amount            bigint,
    interest_rate     integer,
    number_of_months  integer,
    payment_frequency text
);

create table if not exists loan_repayment
(
    id              uuid not null primary key,
    created_on      timestamp,
    updated_on      timestamp,
    payment_amount  float,
    number_od_month integer,
    loan_id         uuid not null,
    constraint fk_lr_loan_id foreign key (loan_id) references loan (id)
);
