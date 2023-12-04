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
