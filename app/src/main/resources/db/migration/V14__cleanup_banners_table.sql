DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns 
                   WHERE table_name='banners' AND column_name='description') THEN
        ALTER TABLE banners ADD COLUMN description TEXT;
    END IF;
END $$;

DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.columns 
               WHERE table_name='banners' AND column_name='s3_image_key') THEN
        ALTER TABLE banners DROP COLUMN s3_image_key;
    END IF;
END $$;
