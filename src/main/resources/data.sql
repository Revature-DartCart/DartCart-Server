INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'HughTheMann',
    '$2a$12$p16sotKqAbR7htIl08ms.eBUfmSvmd2bq0nyPobJONYnE0KmWb72O',
    'Hugh',
    'Mann',
    'hughmanntheman@gmail.com',
    '757-319-0192',
    1645736771148,
    'address'
);

INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'Sammykins',
    '$2a$12$p16sotKqAbR7htIl08ms.eBUfmSvmd2bq0nyPobJONYnE0KmWb72O',
    'Samantha',
    'Mann',
    'sammannnotthefish@gmail.com',
    '757-978-6422',
    1645737413547,
    'address'
);

INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'Caesar92',
    '$2a$12$p16sotKqAbR7htIl08ms.eBUfmSvmd2bq0nyPobJONYnE0KmWb72O',
    'Roe',
    'Mann',
    'thedieiscastxd@gmail.com',
    '757-433-4787',
    1645737476942,
    'address'
);

INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'JerManny',
    '$2a$12$p16sotKqAbR7htIl08ms.eBUfmSvmd2bq0nyPobJONYnE0KmWb72O',
    'Jer',
    'Mann',
    'jermanny@gmail.com',
    '757-670-8879',
    1645737745123,
    'address'
);

INSERT INTO Sellers (
    seller_id,
    name,
    homepage,
    description,
    user_id
)
VALUES (
    DEFAULT,
    'Best Seller 1',
    'bestseller',
    'THE BEST SELLER!',
    1
);

INSERT INTO Sellers (
    seller_id,
    name,
    homepage,
    description,
    user_id
)
VALUES (
    DEFAULT,
    'Best Seller 2',
    'alsobestseller',
    'ALSO THE BEST SELLER!!!',
    4
);

INSERT INTO Shops (
    shop_id,
    location,
    seller_id
)
VALUES (
    DEFAULT,
    'ADDRESS',
    1
);

INSERT INTO Shops (
    shop_id,
    location,
    seller_id
)
VALUES (
    DEFAULT,
    'ADDRESS 2',
    2
);

INSERT INTO Products (product_id, name, description) VALUES
(1, 'Kelloggs Froot Loops', 'Delicious frooty flava');

INSERT INTO Shop_Products (shop_product_id, quantity, price, discount, product_id) VALUES
(1, 10, 15, 2, 1);

INSERT INTO Categories (category_id, name) VALUES (1, 'Food');

INSERT INTO Product_Category (product_id, category_id) VALUES
(1, 1);