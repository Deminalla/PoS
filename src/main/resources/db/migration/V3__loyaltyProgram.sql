use demodatabase;

ALTER TABLE `user`
DROP COLUMN loyalty_points;

DROP TABLE IF EXISTS loyalty;
DROP TABLE IF EXISTS user_loyalty;

CREATE TABLE loyalty (
    loyalty_id VARCHAR(36) NOT NULL,
    business_id VARCHAR(36) NOT NULL,
    description varchar(100) NOT NULL,
    points_required int UNSIGNED NOT NULL,
    PRIMARY KEY (loyalty_id)
);

INSERT INTO loyalty (loyalty_id, business_id, description, points_required)
VALUES ('f073dc63-2bb3-4760-934c-d4fa1b9f391c', 'df8daa3e-f4b2-4279-9ca6-3696af4c477b', '25% discount on purchase', 100),
       ('d4449e86-1f39-4fae-b0ca-37f7272a9c80', 'df8daa3e-f4b2-4279-9ca6-3696af4c477b', 'Free item under 25$', 150),
       ('9401f6f1-b938-438a-94b5-58298114409e', 'df8daa3e-f4b2-4279-9ca6-3696af4c477b', 'Exclusive access to special product', 200);


CREATE TABLE user_loyalty (
    user_loyalty_id VARCHAR(36) NOT NULL,
    loyalty_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    points_acquired int UNSIGNED NOT NULL,
    PRIMARY KEY (user_loyalty_id),
    FOREIGN KEY (loyalty_id) REFERENCES loyalty(loyalty_id),
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
);

INSERT INTO user_loyalty (user_loyalty_id, loyalty_id, user_id, points_acquired)
VALUES ('3385f54b-20e6-4736-9566-60e2858c17c5', 'f073dc63-2bb3-4760-934c-d4fa1b9f391c', 'abef1110-977a-11ee-b9d1-0242ac120002', 50),
       ('ff0ee5cf-f00b-4128-892e-19a83c5c5680', 'd4449e86-1f39-4fae-b0ca-37f7272a9c80', 'abef1110-977a-11ee-b9d1-0242ac120002', 100),
       ('a2d0cdb3-b8db-4e2f-920d-6e0718151169', '9401f6f1-b938-438a-94b5-58298114409e', 'abef13ae-977a-11ee-b9d1-0242ac120002', 150);