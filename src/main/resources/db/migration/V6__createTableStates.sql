CREATE TABLE states
(
    id         BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL UNIQUE,
    country_id BIGINT       NOT NULL,
    CONSTRAINT fk_states_countries FOREIGN KEY (country_id)
        REFERENCES countries (id)
);