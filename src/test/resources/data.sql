--Placeholder table for shop model (fix)
--CREATE TABLE shop (id serial PRIMARY KEY id);

--INSERT INTO shop (id) VALUES (default);

INSERT INTO products (product_id, name, description) VALUES
(1, 'Kelloggs Froot Loops', 'Delicious frooty flava');

INSERT INTO shop_product (shop_product_id, product_id, quantity, price, discount) VALUES
(1, 1, 10, 15.99, 2.99);

INSERT INTO category (category_id, name) VALUES (1, 'Food');

INSERT INTO product_category (product_id, category_id) VALUES
(1, 1);