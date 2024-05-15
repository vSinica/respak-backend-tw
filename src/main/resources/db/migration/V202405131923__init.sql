create table areas
(
    id      bigserial primary key,
    code    bigint,
    name    text    not null,
    archive boolean not null default 'false'
);

create table companys
(
    id           bigserial primary key,
    name         text    not null,
    company_type text    not null,
    inn          text    not null,
    kpp          text    not null,
    ogrn         text    not null,
    reg_area_id  bigint references areas,
    create_date  date,
    archive      boolean not null default 'false'
);

create table company2area
(
    area_id    bigint not null,
    company_id bigint not null
);