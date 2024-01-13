-- apply alter tables
alter table accounts alter column currency set default 'INR';
alter table accounts alter column type set default 'CASH';
alter table settlements alter column currency set default 'INR';
alter table transactions alter column type set default 'EXPENSE';
alter table transactions alter column currency set default 'INR';
alter table users alter column role set default 'USER';
