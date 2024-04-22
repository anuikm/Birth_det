CREATE TABLE birth_registration
(
    id         uuid not null primary key,
    mother_name             varchar(255),
    date_of_birth            date,
    registration_number     varchar(255),
    registration_date        date

);

