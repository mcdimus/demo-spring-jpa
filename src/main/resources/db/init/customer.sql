--liquibase formatted sql

--changeset dmitri.maksimov@hotmail.com:create-table-customer
DROP TABLE IF EXISTS customer CASCADE;

CREATE TABLE customer (
  id         UUID NOT NULL,
  first_name TEXT NOT NULL,
  last_name  TEXT NOT NULL
);

ALTER TABLE customer
  ADD CONSTRAINT pk_customer PRIMARY KEY (id);

--rollback drop table customer;

