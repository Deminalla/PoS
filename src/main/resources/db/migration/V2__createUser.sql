use demodatabase;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    user_id varchar(36) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email varchar(60) NOT NULL,
    password varchar(64) NOT NULL,
    address varchar(50) NOT NULL,
    loyalty_points int UNSIGNED NOT NULL,

    PRIMARY KEY (user_id)
);

INSERT INTO `user` (user_id, first_name, last_name, email, password, address, loyalty_points)
VALUES ('abef1110-977a-11ee-b9d1-0242ac120002', 'Petras', 'Petrauskas', 'petras.petrauskas@gmail.com', 'kazkakazka', 'Didlaukio g. 47', 100),
       ('abef13ae-977a-11ee-b9d1-0242ac120002', 'Evaldas', 'Evaldauskas', 'evaldas.evaldauskas@gmail.com', 'alio123', 'Kauno g. 7', 3000);