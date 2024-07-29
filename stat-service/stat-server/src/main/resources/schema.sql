drop table if exists hits cascade;

create table if not exists hits
(
    id       bigint generated always as identity primary key not null,
    app      varchar(255)                                    not null,
    ip       varchar(15)                                     not null,
    uri      varchar(255)                                    not null,
    method   varchar(10)                                     not null,
    hit_date timestamp without time zone                     not null
);