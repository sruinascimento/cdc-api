CREATE TABLE authors
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(50)         NOT NULL,
    email        VARCHAR(100) UNIQUE NOT NULL,
    description  TEXT(400) NOT NULL,
    registred_at DATE                NOT NULL
);