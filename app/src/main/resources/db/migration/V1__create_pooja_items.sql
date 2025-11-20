

CREATE TABLE IF NOT EXISTS pooja_items (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    item_code VARCHAR(50) NOT NULL UNIQUE,
    item_name VARCHAR(100) NOT NULL,
    description TEXT,
    s3_image_key VARCHAR(500),
    quantity_unit VARCHAR(50),
    estimated_quantity DECIMAL(10, 2),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_tsp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_tsp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

