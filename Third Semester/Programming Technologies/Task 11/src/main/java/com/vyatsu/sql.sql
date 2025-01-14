DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS purchases CASCADE;

CREATE TABLE customers (
                           id INTEGER NOT NULL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);


CREATE TABLE products (
                          id INTEGER NOT NULL PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          price DOUBLE NOT NULL
);


CREATE TABLE purchases (
                           id INTEGER NOT NULL PRIMARY KEY,
                           customer_id BIGINT,
                           product_id BIGINT,
                           purchase_price DOUBLE,
                           FOREIGN KEY (customer_id) REFERENCES customers(id),
                           FOREIGN KEY (product_id) REFERENCES products(id)
);