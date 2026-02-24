

CREATE TABLE IF NOT EXISTS banners (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    item_name VARCHAR(100) NOT NULL,
    item_type VARCHAR(50) NOT NULL,
    s3_image_name VARCHAR(500),
    created_tsp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_tsp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);
CREATE TRIGGER trg_banners_set_created_tsp
BEFORE INSERT ON banners
FOR EACH ROW
EXECUTE FUNCTION fn_set_created_tsp();

CREATE TRIGGER trg_banners_fn_update_updated_tsp
BEFORE INSERT OR UPDATE ON banners
FOR EACH ROW
EXECUTE FUNCTION fn_update_updated_tsp();
