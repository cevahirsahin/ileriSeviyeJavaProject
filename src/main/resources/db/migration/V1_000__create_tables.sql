CREATE TABLE Customer (
    customer_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    billing_address TEXT NOT NULL,
    shipping_address TEXT
);

CREATE TABLE Category (
    category_id BIGSERIAL PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    parent_category_id BIGINT REFERENCES Category(category_id)
);

CREATE TABLE Product (
    product_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    sku VARCHAR(100),
    weight DECIMAL(10, 2),
    dimensions VARCHAR(100),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    category_id BIGINT REFERENCES Category(category_id)
);

CREATE TABLE Invoice (
    invoice_id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL REFERENCES Customer(customer_id),
    invoice_date TIMESTAMP DEFAULT NOW(),
    due_date TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    tax_amount DECIMAL(10, 2),
    discount DECIMAL(10, 2),
    status VARCHAR(50),
    payment_method VARCHAR(50),
    currency VARCHAR(10),
    notes TEXT
);

CREATE TABLE InvoiceItem (
    item_id BIGSERIAL PRIMARY KEY,
    invoice_id BIGINT NOT NULL REFERENCES Invoice(invoice_id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES Product(product_id),
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    discount_amount DECIMAL(10, 2)
);

CREATE TABLE Payment (
    payment_id BIGSERIAL PRIMARY KEY,
    invoice_id BIGINT NOT NULL REFERENCES Invoice(invoice_id) ON DELETE CASCADE,
    payment_date TIMESTAMP DEFAULT NOW(),
    payment_amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50),
    payment_status VARCHAR(50),
    transaction_id VARCHAR(100),
    payment_gateway VARCHAR(50)
);

CREATE TABLE Tax (
    tax_id BIGSERIAL PRIMARY KEY,
    tax_name VARCHAR(255) NOT NULL,
    tax_rate DECIMAL(5, 2) NOT NULL,
    invoice_id BIGINT NOT NULL REFERENCES Invoice(invoice_id) ON DELETE CASCADE,
    applied_amount DECIMAL(10, 2)
);