-- apply changes
create table "group" (
  id                            bigint generated by default as identity not null,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  deleted                       boolean default false not null,
  name                          varchar(255),
  description                   varchar(255),
  avatar                        varchar(255),
  constraint pk_group primary key (id)
);

create table "user" (
  id                            bigint generated by default as identity not null,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  deleted                       boolean default false not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  email                         varchar(255),
  avatar                        varchar(255),
  google_auth_id                varchar(255),
  constraint uq_user_email unique (email),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);

create table user_group (
  user_id                       bigint not null,
  group_id                      bigint not null,
  constraint pk_user_group primary key (user_id,group_id)
);

-- foreign keys and indices
create index ix_user_group_user on user_group (user_id);
alter table user_group add constraint fk_user_group_user foreign key (user_id) references "user" (id) on delete restrict on update restrict;

create index ix_user_group_group on user_group (group_id);
alter table user_group add constraint fk_user_group_group foreign key (group_id) references "group" (id) on delete restrict on update restrict;

