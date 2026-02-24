CREATE TABLE IF NOT EXISTS nuts (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    item_code VARCHAR(50) NOT NULL UNIQUE,
    item_name VARCHAR(100) NOT NULL,
    description TEXT,
    s3_image_name VARCHAR(500),
    total_quantity DECIMAL(10,2),
    quantity_unit VARCHAR(50),
    price DECIMAL(10, 2),
    in_stock BOOLEAN NOT NULL DEFAULT TRUE,
    stock_in_quantity INT DEFAULT 0,
    created_tsp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_tsp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);
INSERT INTO nuts (item_code, item_name, description, s3_image_name, total_quantity, quantity_unit, price, in_stock,stock_in_quantity)
VALUES
('NUT-001', 'Cashew', 'Premium quality whole cashews', 'cashew.png', 500, 'gms', 800.00, true,20),
('NUT-002', 'Masala Cashew', 'Spicy and roasted masala cashews', 'masala_cashew.png', 250, 'gms', 500.00, true,20);
