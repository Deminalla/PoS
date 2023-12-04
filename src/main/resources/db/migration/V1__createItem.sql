use demodatabase;

DROP TABLE IF EXISTS item;

CREATE TABLE item (
    name varchar(45) NOT NULL,
    price decimal (20,2) NOT NULL,
    description varchar(100) NOT NULL,
    category_id varchar(45) NOT NULL,
    type varchar(30) NOT NULL,
    item_id bigint(20) UNSIGNED NOT NULL,
    business_id varchar(15) NOT NULL,
    out_of_stock TINYINT(1) NOT NULL,
    PRIMARY KEY (item_id)
);

INSERT INTO item (name, price, description, category_id, type, item_id, business_id, out_of_stock)
VALUES ('Stale bread', 12.99,'This stale bread has been sitting on the shelf for weeks', 'C0001', 'Product',
        10001010101001, 'B0001', false),
       ('Ice cream', 3.99, 'This ice cream is delicious', 'C0001', 'Product',
        10001010101002, 'B0001', true);
