create table if not exists loan
(
    id                    uuid not null primary key,
    created_on            timestamp,
    updated_on            timestamp,
    amount                numeric,
    interest_rate         integer,
    number_of_months      bigint,
    payment_frequency     text,
    total_interest_amount numeric
);
alter index if exists loan_pkey rename to ind_l_id;

create table if not exists installment
(
    id              uuid not null primary key,
    created_on      timestamp,
    updated_on      timestamp,
    amount          numeric,
    number_of_month bigint,
    loan_id         uuid not null,
    constraint fk_i_loan_id foreign key (loan_id) references loan (id)
);
alter index if exists installment_pkey rename to ind_i_id;
create index if not exists ind_i_loan_id on installment (loan_id);
