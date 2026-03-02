create table product (
    id uuid primary key,

    name varchar(255) not null,
    description varchar(500),

    price numeric(10,2) not null,

    active boolean not null,

    created_at timestamp not null,
    updated_at timestamp not null
);