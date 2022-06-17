create table conta (
id BIGSERIAL PRIMARY KEY NOT NULL,
code varchar(100) not null,
cpf varchar(11) not null,
product varchar(100) not null,
unique(cpf)
);

create table extrato(
id BIGSERIAL PRIMARY KEY NOT NULL,
conta varchar(255) not null,
debit decimal(10,4) not null,
credit decimal(10,4) not null,
balance decimal(10,5) not null,
date_extrato timestamp not null,
describe_registry varchar(100) not null
);