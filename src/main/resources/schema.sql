DROP TABLE IF EXISTS DEPARTAMENTS CASCADE;
CREATE TABLE DEPARTAMENTS(
    departament_id SERIAL NOT NULL,
    name    VARCHAR(50) NOT NULL,
    PRIMARY KEY (departament_id)
);

DROP TABLE IF EXISTS EMPLOYEES;
CREATE TABLE EMPLOYEES(
    employee_id SERIAL NOT NULL,
    first_name  VARCHAR(20) NOT NULL,
    first_last_name  VARCHAR(20) NOT NULL,
    second_last_name  VARCHAR(20) NOT NULL,
    middle_name  VARCHAR(50),
    email VARCHAR(300) NOT NULL,
    job_country  VARCHAR(50) NOT NULL,
    document_type  VARCHAR(50) NOT NULL,
    document_number  VARCHAR(20) NOT NULL,
    entry_date Date,
    departament_id INT NOT NULL,
    status  VARCHAR(50) NOT NULL,
    insert_date Date,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (departament_id) REFERENCES DEPARTAMENTS(departament_id)
);

