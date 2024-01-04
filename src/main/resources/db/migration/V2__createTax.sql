use demodatabase;

DROP TABLE IF EXISTS tax;

CREATE TABLE tax (
    tax_id varchar(36) NOT NULL,
    amount_pct decimal(7,2) NOT NULL,
    description varchar(100) NOT NULL,
    PRIMARY KEY (tax_id)
);

INSERT INTO tax (tax_id, amount_pct, description)
VALUES ('1cdc7e53-4771-4dcc-bed3-ac9d36623502', 21.00, "Standart Lithuanian tax"),
       ('86a70067-4250-4f05-bdf9-88a540b71fde', 9.00, "Reduced Lithuanian tax");
