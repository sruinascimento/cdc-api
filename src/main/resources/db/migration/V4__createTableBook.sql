CREATE TABLE books (
    id               BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    title            VARCHAR(255) UNIQUE NOT NULL,
    summary          TEXT NOT NULL,
    table_of_contents TEXT,
    price            DECIMAL(10,2) NOT NULL,
    number_of_pages  INT NOT NULL,
    isbn             VARCHAR(13) UNIQUE NOT NULL,
    publication_date DATE,
    category_id      BIGINT NOT NULL,
    author_id        BIGINT NOT NULL,
    FOREIGN KEY (category_id)
        REFERENCES categories (id),
    FOREIGN KEY (author_id)
        REFERENCES authors (id)
);