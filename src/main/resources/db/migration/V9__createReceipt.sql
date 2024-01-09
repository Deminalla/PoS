use demodatabase;

DROP TABLE IF EXISTS receipt;

CREATE TABLE receipt (
    receipt_id varchar(36) NOT NULL,
    order_id varchar(36) NOT NULL,
    customer_id varchar(36) NOT NULL,
    loyalty_id varchar(36) NOT NULL,
    tax_id varchar(36) NOT NULL,
    price double DEFAULT 0,
    PRIMARY KEY (receipt_id),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id),
    FOREIGN KEY (customer_id) REFERENCES `user`(user_id),
    FOREIGN KEY (loyalty_id) REFERENCES loyalty(loyalty_id),
    FOREIGN KEY (tax_id) REFERENCES tax(tax_id)
);

INSERT INTO receipt (receipt_id, order_id, customer_id, loyalty_id, tax_id, price)
VALUES ('8a2deab3-8040-4b91-824f-78a4ce4781b4', 'fae1efaf-32b9-4080-955a-86a800097f78', 'abef1110-977a-11ee-b9d1-0242ac120002',
        'f073dc63-2bb3-4760-934c-d4fa1b9f391c', '1cdc7e53-4771-4dcc-bed3-ac9d36623502', 0),
       ('d2ec7193-100f-48f7-9d82-1590d5a16f56', 'e3cfd81d-2cc7-47b5-b0a8-2e1b536b14d7', 'abef13ae-977a-11ee-b9d1-0242ac120002',
        'd4449e86-1f39-4fae-b0ca-37f7272a9c80', '86a70067-4250-4f05-bdf9-88a540b71fde', 10);