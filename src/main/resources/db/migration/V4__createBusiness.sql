use demodatabase;
DROP TABLE IF EXISTS business;

CREATE TABLE business (
    business_id VARCHAR(36) NOT NULL,
    name varchar(55) NOT NULL,
    address varchar(100) NOT NULL,
    PRIMARY KEY (business_id)
);

INSERT INTO business (business_id, name, address)
VALUES
    ('df8daa3e-f4b2-4279-9ca6-3696af4c477b', 'Business Name 1', '123 Main Street, Kaunas, Lithuania'),
    ('01021d3c-fb37-4527-aefc-73c6c4eaac61', 'Business Name 2', '456 Elm Street, Milan, Italy');




ALTER TABLE item
ADD FOREIGN KEY (business_id)
REFERENCES business(business_id);

ALTER TABLE loyalty
ADD FOREIGN KEY (business_id)
REFERENCES business(business_id);
