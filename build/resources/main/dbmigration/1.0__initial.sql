-- apply changes
create table accounts (
  id                            bigint generated by default as identity not null,
  is_default                    boolean default false not null,
  user_id                       bigint,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  name                          varchar(255),
  currency                      varchar(3) default 'INR',
  balance                       varchar(255),
  type                          varchar(18) default 'CASH',
  constraint ck_accounts_currency check ( currency in ('INR','USD')),
  constraint ck_accounts_type check ( type in ('CASH','CREDIT_CARD','DEBIT_CARD','BANK_ACCOUNT','INVESTMENT_ACCOUNT','LOAN','OTHER')),
  constraint pk_accounts primary key (id)
);

create table categories (
  id                            bigint generated by default as identity not null,
  user_id                       bigint,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  name                          varchar(255) not null,
  description                   varchar(255),
  avatar                        varchar(255),
  constraint pk_categories primary key (id)
);

create table friendships (
  id                            bigint generated by default as identity not null,
  user_id                       bigint,
  friend_id                     bigint,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  friendship_id                 varchar(255),
  status                        varchar(8) default 'PENDING',
  constraint ck_friendships_status check ( status in ('PENDING','ACCEPTED','REJECTED','BLOCKED')),
  constraint uq_friendships_friendship_id unique (friendship_id),
  constraint pk_friendships primary key (id)
);

create table groups (
  id                            bigint generated by default as identity not null,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  name                          varchar(255) not null,
  description                   varchar(255),
  avatar                        varchar(255),
  constraint pk_groups primary key (id)
);

create table recurring_transactions (
  id                            bigint generated by default as identity not null,
  start_date                    date,
  end_date                      date,
  frequency                     integer not null,
  next_execution_date           date,
  is_active                     boolean default false not null,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  interval                      varchar(255),
  recurring_period              varchar(255),
  constraint pk_recurring_transactions primary key (id)
);

create table settlements (
  id                            bigint generated by default as identity not null,
  amount                        float not null,
  date                          date,
  payer_id                      bigint,
  receiver_id                   bigint,
  group_id                      bigint,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  currency                      varchar(3) default 'INR' not null,
  constraint ck_settlements_currency check ( currency in ('INR','USD')),
  constraint pk_settlements primary key (id)
);

create table transactions (
  id                            bigint generated by default as identity not null,
  amount                        float not null,
  date                          date,
  is_shared                     boolean default false not null,
  is_recurring                  boolean default false not null,
  recurring_id                  bigint,
  category_id                   bigint,
  account_id                    bigint,
  group_id                      bigint,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  description                   varchar(255),
  type                          varchar(13) default 'EXPENSE' not null,
  currency                      varchar(3) default 'INR' not null,
  constraint ck_transactions_type check ( type in ('INCOME','EXPENSE','SELF_TRANSFER','INVESTMENT')),
  constraint ck_transactions_currency check ( currency in ('INR','USD')),
  constraint pk_transactions primary key (id)
);

create table users (
  id                            bigint generated by default as identity not null,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  email                         varchar(255) not null,
  avatar                        varchar(255),
  google_auth_id                varchar(255),
  role                          varchar(5) default 'USER' not null,
  constraint ck_users_role check ( role in ('USER','ADMIN')),
  constraint uq_users_email unique (email),
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id)
);

create table user_groups (
  id                            bigint generated by default as identity not null,
  user_id                       bigint,
  group_id                      bigint,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  constraint pk_user_groups primary key (id)
);

create table user_transactions (
  id                            bigint generated by default as identity not null,
  creator_id                    bigint,
  receiver_id                   bigint,
  payer_id                      bigint,
  transaction_id                bigint,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  is_deleted                    boolean default false not null,
  constraint pk_user_transactions primary key (id)
);

-- foreign keys and indices
create index ix_accounts_user_id on accounts (user_id);
alter table accounts add constraint fk_accounts_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;

create index ix_categories_user_id on categories (user_id);
alter table categories add constraint fk_categories_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;

create index ix_settlements_payer_id on settlements (payer_id);
alter table settlements add constraint fk_settlements_payer_id foreign key (payer_id) references users (id) on delete restrict on update restrict;

create index ix_settlements_receiver_id on settlements (receiver_id);
alter table settlements add constraint fk_settlements_receiver_id foreign key (receiver_id) references users (id) on delete restrict on update restrict;

create index ix_settlements_group_id on settlements (group_id);
alter table settlements add constraint fk_settlements_group_id foreign key (group_id) references groups (id) on delete restrict on update restrict;

create index ix_transactions_recurring_id on transactions (recurring_id);
alter table transactions add constraint fk_transactions_recurring_id foreign key (recurring_id) references recurring_transactions (id) on delete restrict on update restrict;

create index ix_transactions_category_id on transactions (category_id);
alter table transactions add constraint fk_transactions_category_id foreign key (category_id) references categories (id) on delete restrict on update restrict;

create index ix_transactions_account_id on transactions (account_id);
alter table transactions add constraint fk_transactions_account_id foreign key (account_id) references accounts (id) on delete restrict on update restrict;

create index ix_transactions_group_id on transactions (group_id);
alter table transactions add constraint fk_transactions_group_id foreign key (group_id) references groups (id) on delete restrict on update restrict;

create index ix_user_groups_user_id on user_groups (user_id);
alter table user_groups add constraint fk_user_groups_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;

create index ix_user_groups_group_id on user_groups (group_id);
alter table user_groups add constraint fk_user_groups_group_id foreign key (group_id) references groups (id) on delete restrict on update restrict;

create index ix_user_transactions_creator_id on user_transactions (creator_id);
alter table user_transactions add constraint fk_user_transactions_creator_id foreign key (creator_id) references users (id) on delete restrict on update restrict;

create index ix_user_transactions_receiver_id on user_transactions (receiver_id);
alter table user_transactions add constraint fk_user_transactions_receiver_id foreign key (receiver_id) references users (id) on delete restrict on update restrict;

create index ix_user_transactions_payer_id on user_transactions (payer_id);
alter table user_transactions add constraint fk_user_transactions_payer_id foreign key (payer_id) references users (id) on delete restrict on update restrict;

create index ix_user_transactions_transaction_id on user_transactions (transaction_id);
alter table user_transactions add constraint fk_user_transactions_transaction_id foreign key (transaction_id) references transactions (id) on delete restrict on update restrict;

