CREATE SCHEMA IF NOT EXISTS eurder;

CREATE TABLE items
(
    item_id              uuid not null
        constraint items_pk
            primary key,
    item_name            varchar,
    item_description     varchar,
    item_amount_in_stock integer,
    item_price_amount    double precision,
    item_price_currency  varchar
);