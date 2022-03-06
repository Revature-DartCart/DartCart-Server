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
    '16e168e4cb8c6921dc2bb3f1e11c87a12b4fa3c750ca14efeb7f8870ab611971',
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
    '3e4463134cf818db84a14d750b86603aa0611b1989c337d4bc9f0a14e3a08bb1',
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
    'ac9ef06fa6d05293997edd051dee190ae657e5033a612b6977fe0fd6b227d99e',
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
    'bb45d85472de2bff5e1801cf09d3b0b5121a29e5e71bc128f1621ccf9d9d410f',
    'Jer',
    'Mann',
    'jermanny@gmail.com',
    '757-670-8879',
    1645737745123,
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
    'test',
    '$2a$04$vwsthDC7JUu9kfqA264DJenCuUC2ifUcRAGdEUFNrxa/gz9p9rgC2',
    'Jer',
    'Mann',
    'jermanny@gmail.com',
    '757-670-8879',
    1645737745123,
    'address'
);

INSERT INTO Sellers (
    seller_id,
    homepage,
    description,
    user_id
)
VALUES (
    DEFAULT,
    'bestseller',
    'THE BEST SELLER!',
    1
);

INSERT INTO Sellers (
    seller_id,
    homepage,
    description,
    user_id
)
VALUES (
    DEFAULT,
    'alsobestseller',
    'ALSO THE BEST SELLER!!!',
    4
);
INSERT INTO Products (product_id, name, description) VALUES
(1, 'Kelloggs Froot Loops', 'Delicious frooty flava');

INSERT INTO Shop_Products (shop_product_id, quantity, price, discount, product_id) VALUES
(1, 10, 15, 2, 1);

INSERT INTO Categories (category_id, name) VALUES (1, 'Food');

INSERT INTO Product_Category (product_id, category_id) VALUES
(1, 1);