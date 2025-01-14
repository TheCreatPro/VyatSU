DROP TABLE products;
CREATE TABLE IF NOT EXISTS products (
                                        id SERIAL PRIMARY KEY,
                                        title VARCHAR(255) NOT NULL,
                                        price INTEGER NOT NULL
);
