use demodatabase;

DROP TABLE IF EXISTS item;

CREATE TABLE item (
    name varchar(45) NOT NULL,
    price decimal (20,2) NOT NULL,
    description varchar(100) NOT NULL,
    category_id varchar(36) NOT NULL,
    type varchar(30) NOT NULL,
    item_id VARCHAR(36) NOT NULL,
    business_id VARCHAR(36) NOT NULL,
    out_of_stock TINYINT(1) NOT NULL,
    PRIMARY KEY (item_id)
);

INSERT INTO item (name, price, description, category_id, type, item_id, business_id, out_of_stock)
VALUES ('Stale bread', 12.99,'This stale bread has been sitting on the shelf for weeks', 'cfdd9376-5d94-4396-aa8b-854ceeb64802', 'Product',
        '4d9c2f1c-3836-42c8-a0a5-03d365868908', 'df8daa3e-f4b2-4279-9ca6-3696af4c477b', false),
       ('Ice cream', 3.99, 'This ice cream is delicious', 'cfdd9376-5d94-4396-aa8b-854ceeb64802', 'Product',
        'daf3fbea-707e-48d4-a4bc-377e5047f9cb', 'df8daa3e-f4b2-4279-9ca6-3696af4c477b', true),
       ('Cake', 9.99, 'This cake is delicious', 'cfdd9376-5d94-4396-aa8b-854ceeb64802', 'Product',
        'a632334b-0ab2-4fa8-a34a-ba8c91496893', 'df8daa3e-f4b2-4279-9ca6-3696af4c477b', true);
