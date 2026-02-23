UPDATE occasions
SET
    s3_image_name = description,
    description = s3_image_name
WHERE created_by = 'SYSTEM';