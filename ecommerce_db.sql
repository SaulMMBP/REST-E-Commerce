CREATE DATABASE IF NOT EXISTS ecommerce_db;

USE ecommerce_db;

CREATE TABLE categories (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products (
	id BIGINT NOT NULL AUTO_INCREMENT,
    sku VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    unit_price DECIMAL(5, 2) NOT NULL,
    image_url VARCHAR(100) NOT NULL,
    active BIT(1) NOT NULL,
    units_in_stock INT NOT NULL,
    date_created DATETIME NOT NULL,
    last_updated DATETIME NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE customers (
	id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE addresses (
	id BIGINT NOT NULL AUTO_INCREMENT,
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    zip_code VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders (
	id BIGINT NOT NULL AUTO_INCREMENT,
    order_tracking_number VARCHAR(100) NOT NULL,
    total_price DECIMAL(5,2) NOT NULL,
    total_quantity INT NOT NULL,
    status VARCHAR(100),
    date_created DATETIME NOT NULL,
    last_updated DATETIME NOT NULL,
    billing_address_id BIGINT NOT NULL,
    shipping_address_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_billing_addres FOREIGN KEY (billing_address_id) REFERENCES addresses(id),
    CONSTRAINT FK_shipping_addres FOREIGN KEY (shipping_address_id) REFERENCES addresses(id),
    CONSTRAINT FK_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE order_items (
	id BIGINT NOT NULL AUTO_INCREMENT,
    image_url VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(5,2) NOT NULL,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT FK_product FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO categories(name) VALUES ('BOOKS');

INSERT INTO products(sku, name, description, unit_price, image_url, active, units_in_stock, date_created, last_updated, category_id)
VALUES ('BOOK-TECH-1000', 'JavaScript - The Fun Parts', 'Learn JavaScript', 19.99, 'assets/images/products/placeholder.png', 1, 100, NOW(), NOW(), 1);

INSERT INTO products(sku, name, description, unit_price, image_url, active, units_in_stock, date_created, last_updated, category_id)
VALUES ('BOOK-TECH-1001', 'Spring Framework Tutorial', 'Learn Spring',29.99,'assets/images/products/placeholder.png',1,100, NOW(), NOW(), 1);

INSERT INTO products(sku, name, description, unit_price, image_url, active, units_in_stock, date_created, last_updated, category_id)
VALUES ('BOOK-TECH-1002', 'Kubernetes - Deploying Containers', 'Learn Kubernetes',24.99,'assets/images/products/placeholder.png',1,100, NOW(), NOW(), 1);

INSERT INTO products(sku, name, description, unit_price, image_url, active, units_in_stock, date_created, last_updated, category_id)
VALUES ('BOOK-TECH-1003', 'Internet of Things (IoT) - Getting Started', 'Learn IoT',29.99,'assets/images/products/placeholder.png',1,100, NOW(), NOW(), 1);

INSERT INTO products(sku, name, description, unit_price, image_url, active, units_in_stock, date_created, last_updated, category_id)
VALUES ('BOOK-TECH-1004', 'The Go Programming Language: A to Z', 'Learn Go',24.99,'assets/images/products/placeholder.png',1,100, NOW(), NOW(), 1);

