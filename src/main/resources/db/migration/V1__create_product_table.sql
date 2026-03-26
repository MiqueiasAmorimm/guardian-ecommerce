CREATE TABLE product (
    id UUID PRIMARY KEY,

    name VARCHAR(255) NOT NULL,
    description VARCHAR(500) NOT NULL,

    price NUMERIC(10,2) NOT NULL,

    active BOOLEAN NOT NULL,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);