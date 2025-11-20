
CREATE OR REPLACE FUNCTION fn_set_created_tsp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.created_tsp := CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION fn_update_updated_tsp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_tsp := CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_occasions_set_created_tsp
BEFORE INSERT ON occasions
FOR EACH ROW
EXECUTE FUNCTION fn_set_created_tsp();

CREATE TRIGGER trg_occasions_update_updated_tsp
BEFORE INSERT OR UPDATE ON occasions
FOR EACH ROW
EXECUTE FUNCTION fn_update_updated_tsp();


CREATE TRIGGER trg_pooja_items_set_created_tsp
BEFORE INSERT ON pooja_items
FOR EACH ROW
EXECUTE FUNCTION fn_set_created_tsp();

CREATE TRIGGER trg_pooja_items_update_updated_tsp
BEFORE INSERT OR UPDATE ON pooja_items
FOR EACH ROW
EXECUTE FUNCTION fn_update_updated_tsp();


CREATE TRIGGER trg_mapping_set_created_tsp
BEFORE INSERT ON pooja_item_occasion_mapping
FOR EACH ROW
EXECUTE FUNCTION fn_set_created_tsp();

CREATE TRIGGER trg_mapping_update_updated_tsp
BEFORE INSERT OR UPDATE ON pooja_item_occasion_mapping
FOR EACH ROW
EXECUTE FUNCTION fn_update_updated_tsp();