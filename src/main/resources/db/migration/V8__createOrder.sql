use demodatabase;

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
    order_id varchar(36) NOT NULL,
    customer_id varchar(36) NOT NULL,
    creation_date date NOT NULL,
    pending_until datetime NOT NULL,
    paid_date_time datetime NULL,
    gratuity float DEFAULT 0,
    order_status enum ('pending', 'cancelled', 'paid') NOT NULL DEFAULT 'pending',
    loyalty_program_id varchar(36) NULL,
    tax_rate_id varchar(36) NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (customer_id) REFERENCES `user`(user_id),
    FOREIGN KEY (tax_rate_id) REFERENCES tax(tax_id),
    FOREIGN KEY (loyalty_program_id) REFERENCES user_loyalty(user_loyalty_id)
);

INSERT INTO `order` (order_id, customer_id, creation_date, pending_until, paid_date_time, gratuity, order_status, loyalty_program_id, tax_rate_id)
VALUES ('fae1efaf-32b9-4080-955a-86a800097f78', 'abef1110-977a-11ee-b9d1-0242ac120002', '2023-12-12', '2023-12-14 14:30:00',
        '2023-12-14 16:45:00', 2.1, 'pending', '3385f54b-20e6-4736-9566-60e2858c17c5', '1cdc7e53-4771-4dcc-bed3-ac9d36623502'),
       ('e3cfd81d-2cc7-47b5-b0a8-2e1b536b14d7', 'abef13ae-977a-11ee-b9d1-0242ac120002', '2023-12-15', '2023-12-17 15:00:00',
        '2023-12-17 18:30:00', 3.5, 'pending', 'ff0ee5cf-f00b-4128-892e-19a83c5c5680', '86a70067-4250-4f05-bdf9-88a540b71fde');


DROP TABLE IF EXISTS order_employee;

CREATE TABLE order_employee (
    order_employee_id varchar(36) NOT NULL,
    order_id varchar(36) NOT NULL,
    employee_id varchar(36) NOT NULL,
    PRIMARY KEY (order_employee_id),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

INSERT INTO order_employee (order_employee_id, order_id, employee_id)
VALUES ('6454393b-77a6-4cda-a550-88647d74f936', 'fae1efaf-32b9-4080-955a-86a800097f78', 'c833b910-df33-4a7c-aea5-d23954361433'),
       ('40137e6d-304e-4eea-97ae-cb196ccec2f8', 'e3cfd81d-2cc7-47b5-b0a8-2e1b536b14d7', 'e5fdfab6-40aa-4eec-8ad6-eb36625d3f60');


DROP TABLE IF EXISTS order_item;

CREATE TABLE order_item (
    order_item_id varchar(36) NOT NULL,
    order_id varchar(36) NOT NULL,
    item_id varchar(36) NOT NULL,
    quantity int NOT NULL,
    PRIMARY KEY (order_item_id),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id),
    FOREIGN KEY (item_id) REFERENCES item(item_id)
);

INSERT INTO order_item (order_item_id, order_id, item_id, quantity)
VALUES ('dfea6159-cfd1-4e65-a7e8-469a77954886', 'fae1efaf-32b9-4080-955a-86a800097f78', 'a632334b-0ab2-4fa8-a34a-ba8c91496893', 1),
       ('a5c7b6e6-575f-47e9-88e2-5c45b97ee8d8', 'e3cfd81d-2cc7-47b5-b0a8-2e1b536b14d7', 'daf3fbea-707e-48d4-a4bc-377e5047f9cb', 1);