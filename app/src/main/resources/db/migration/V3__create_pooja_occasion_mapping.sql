
CREATE TABLE IF NOT EXISTS pooja_item_occasion_mapping (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pooja_item_id BIGINT NOT NULL REFERENCES pooja_items(id) ON DELETE CASCADE,
    occasion_id BIGINT NOT NULL REFERENCES occasions(id) ON DELETE CASCADE,
    notes TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_tsp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_tsp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    UNIQUE(pooja_item_id, occasion_id)
);