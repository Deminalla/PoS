use demodatabase;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    user_id varchar(36) NOT NULL,
    employee_id varchar(36) NOT NULL,
    employee_since date NOT NULL,
    job_title varchar(36) NOT NULL,

    PRIMARY KEY (employee_id),
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
);

INSERT INTO employee (user_id, employee_id, employee_since, job_title)
VALUES ('abef1110-977a-11ee-b9d1-0242ac120002', 'c833b910-df33-4a7c-aea5-d23954361433', '2017-08-14', 'Waiter'),
       ('abef13ae-977a-11ee-b9d1-0242ac120002', 'e5fdfab6-40aa-4eec-8ad6-eb36625d3f60', '2023-07-03', 'Manager');