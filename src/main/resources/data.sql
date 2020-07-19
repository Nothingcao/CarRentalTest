DROP TABLE IF EXISTS car;
CREATE TABLE car(car_no bigint PRIMARY KEY, brand_name VARCHAR(100));
INSERT INTO car(car_no,brand_name) VALUES(1,'Toyota Camry');
INSERT INTO car(car_no,brand_name) VALUES(2,'Toyota Camry');
INSERT INTO car(car_no,brand_name) VALUES(3,'BMW 650');
INSERT INTO car(car_no,brand_name) VALUES(4,'BMW 650');

DROP TABLE IF EXISTS car_schedule;
CREATE TABLE car_schedule(id bigint  PRIMARY KEY,car_no bigint, scheduled_date VARCHAR(100));




COMMIT;