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
(1, 'LEGOs', 'This is a toy!'),
(2, 'Blue Jeans', 'This is clothing!'),
(3, 'La-Z-Boy', 'This is furniture!'),
(4, 'PlayStation 5', 'This is an entertainment!'),
(5, 'Pennzoil', 'This is automotive!'),
(6, 'Vacuum Cleaner', 'This is home goods!'),
(7, 'Notepad', 'This is school & office!'),
(8, 'Shampoo', 'This is personal care!');

INSERT INTO Shop_Products (shop_product_id, quantity, price, discount, product_id) VALUES
(1, 10, 15, 2, 1);

INSERT INTO Categories (category_id, name) VALUES
(1, 'Toys'),
(2, 'Clothing'),
(3, 'Furniture'),
(4, 'Entertainment'),
(5, 'Automotive'),
(6, 'Home Goods'),
(7, 'School & Office'),
(8, 'Personal Care');

INSERT INTO Product_Category (product_id, category_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);