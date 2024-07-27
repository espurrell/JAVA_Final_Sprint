-- Insert Admins
INSERT INTO Users (username, password, email, role) VALUES
('admin1', 'adminpass1', 'admin1@vintique.com', 'admin'),
('admin2', 'adminpass2', 'admin2@vintique.com', 'admin'),
('admin3', 'adminpass3', 'admin3@vintique.com', 'admin'),
('admin4', 'adminpass4', 'admin4@vintique.com', 'admin'),
('admin5', 'adminpass5', 'admin5@vintique.com', 'admin');

-- Insert Sellers
INSERT INTO Users (username, password, email, role) VALUES
('seller1', 'sellerpass1', 'seller1@vintique.com', 'seller'),
('seller2', 'sellerpass2', 'seller2@vintique.com', 'seller'),
('seller3', 'sellerpass3', 'seller3@vintique.com', 'seller'),
('seller4', 'sellerpass4', 'seller4@vintique.com', 'seller'),
('seller5', 'sellerpass5', 'seller5@vintique.com', 'seller');

-- Insert Buyers
INSERT INTO Users (username, password, email, role) VALUES
('buyer1', 'buyerpass1', 'buyer1@vintique.com', 'buyer'),
('buyer2', 'buyerpass2', 'buyer2@vintique.com', 'buyer'),
('buyer3', 'buyerpass3', 'buyer3@vintique.com', 'buyer'),
('buyer4', 'buyerpass4', 'buyer4@vintique.com', 'buyer'),
('buyer5', 'buyerpass5', 'buyer5@vintique.com', 'buyer');

-- Insert Products
INSERT INTO Products (item_name, item_type, item_description, seller_id) VALUES
('Vintage Lamp', 'Home Decor', 'A beautiful vintage lamp from the 1920s', 6),
('Antique Vase', 'Home Decor', 'A rare antique vase with intricate designs', 7),
('Retro Radio', 'Electronics', 'A fully functional retro radio from the 1950s', 8),
('Classic Record Player', 'Electronics', 'A classic record player in great condition', 9),
('Vintage Camera', 'Photography', 'A vintage camera with all original parts', 10),
('Old Book Collection', 'Books', 'A collection of rare old books', 6),
('Antique Watch', 'Accessories', 'A classic antique watch in excellent condition', 7),
('Vintage Dress', 'Clothing', 'A beautiful vintage dress from the 1960s', 8),
('Retro Sunglasses', 'Accessories', 'Stylish retro sunglasses', 9),
('Classic Bicycle', 'Sports', 'A classic bicycle with a vintage design', 10);
