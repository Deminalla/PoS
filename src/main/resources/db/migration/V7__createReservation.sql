use demodatabase;

DROP TABLE IF EXISTS reservation;

CREATE TABLE reservation (
    reservation_id varchar(36) NOT NULL,
    item_id varchar(36) NOT NULL,
    employee_id varchar(36) NOT NULL,
    customer_id varchar(36),
    res_start timestamp NOT NULL,
    res_end timestamp NOT NULL,
    is_reserved TINYINT(1) NOT NULL,
    description varchar(100) NOT NULL,
    PRIMARY KEY (reservation_id)
);

INSERT INTO reservation (reservation_id, item_id, employee_id, customer_id, res_start, res_end, is_reserved, description)
VALUES ('88bb48f6-bfb5-4bac-a5e3-781835f1bb50', '4d9c2f1c-3836-42c8-a0a5-03d365868908',
        'c833b910-df33-4a7c-aea5-d23954361433', null, '2023-12-25 16:00:00', '2023-12-25 16:30:00', 0, 'bread reservation'),
        ('ec99bd0f-4e9a-4dba-96f3-53fef35e2790', 'a632334b-0ab2-4fa8-a34a-ba8c91496893',
        'e5fdfab6-40aa-4eec-8ad6-eb36625d3f60', null, '2023-12-26 10:00:00', '2023-12-26 20:00:00', 0, 'cake reservation');
