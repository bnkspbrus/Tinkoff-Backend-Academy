--liquibase formatted sql

--changeset bnkspbrus:2
CREATE TABLE client_type_v2
(
    id       INT,
    typeName VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO client_type_v2
VALUES (1, 'handyman'),
       (2, 'rancher');

ALTER TABLE client
    ADD clientTypeV2 INT REFERENCES client_type_v2 (id);

UPDATE client
SET clientTypeV2 = 1
WHERE clientType = 'handyman';

UPDATE client
SET clientTypeV2 = 2
WHERE clientType = 'rancher';

ALTER TABLE client DROP clientType;