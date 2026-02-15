CREATE INDEX idx_occasions_code ON occasions(occasion_code);
CREATE INDEX idx_occasions_active ON occasions(is_active);
CREATE INDEX idx_pooja_items_code ON pooja_items(item_code);
CREATE INDEX idx_pooja_items_active ON pooja_items(in_stock);
CREATE INDEX idx_mapping_occasion ON pooja_item_occasion_mapping(occasion_id);
CREATE INDEX idx_mapping_pooja_item ON pooja_item_occasion_mapping(pooja_item_id);
CREATE INDEX idx_mapping_active ON pooja_item_occasion_mapping(is_active);
