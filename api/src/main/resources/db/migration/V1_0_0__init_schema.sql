CREATE TABLE pharmacies (
    id bigserial primary key,
<<<<<<< HEAD
    name varchar(255) not null,
    medicine_link_template varchar(255)
=======
    name varchar(256) not null,
    medicine_link_template varchar(256)
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
);

CREATE TABLE medicines (
    id bigserial primary key,
<<<<<<< HEAD
    title varchar(255)
=======
    title varchar(256)
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
);

CREATE TABLE prices (
    pharmacy_id bigint,
    medicine_id bigint,
<<<<<<< HEAD
    price decimal(10, 2) not null default 0,
    external_id varchar(100) not null,
    updated_at timestamp not null default now(),
    PRIMARY KEY (pharmacy_id, medicine_id)
);
=======
    price decimal(10, 2) not null,
    external_id varchar(100) not null,
    updated_at timestamp not null default now(),
    PRIMARY KEY (pharmacy_id, medicine_id)
);
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
