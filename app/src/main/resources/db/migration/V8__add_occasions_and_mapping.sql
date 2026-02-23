-- 0️⃣ Add s3_image_name column
ALTER TABLE occasions ADD COLUMN IF NOT EXISTS s3_image_name VARCHAR(255);

-- 1️⃣ Insert Occasions
INSERT INTO occasions (occasion_code, occasion_name,s3_image_name, description, created_by)
VALUES
('ENGAGEMENT', 'Engagement', 'Engagement ceremony items','engagement.jpeg', 'SYSTEM'),
('PASUPU_DHANCHADAM', 'Pasupu Dhanchadam', 'Pasupu Dhanchadam ceremony items','pasupu_dhanchadam.jpeg', 'SYSTEM'),
('PANDIRI_RAATA', 'Pandiri Raata', 'Pandiri Raata ritual items','pandiri_rata.jpg','SYSTEM'),
('PELLI_KODUKU_KUTHURU', 'Pelli Koduku / Pelli Kuthuru', 'Bride & Groom ritual items','pelli_koduku_koothuru.jpg', 'SYSTEM'),
('HALDI', 'Haldi', 'Haldi ceremony items', 'haldi.jpeg','SYSTEM'),
('MARRIAGE', 'Marriage', 'Wedding ceremony items','marriage.jpeg', 'SYSTEM'),
('SATYANARAYANA_POOJA', 'Satyanarayana Pooja', 'Satyanarayana pooja items','satya_narayana_pooja.jpeg', 'SYSTEM')
ON CONFLICT (occasion_code) DO NOTHING;








-- 2️⃣ Insert Mapping
INSERT INTO pooja_item_occasion_mapping (pooja_item_id, occasion_id, created_by)
SELECT p.id, o.id, 'SYSTEM'
FROM pooja_items p
JOIN occasions o ON TRUE
WHERE

-- ENGAGEMENT
(
    o.occasion_code = 'ENGAGEMENT'
    AND p.item_code IN (
        'TURMERIC','KUMKUM','TURMERIC_BULLETS',
        'CHANDAN_POWDER','BETEL_LEAVES',
        'AKSHINTHALU','TENDER_COCONUT',
        'PUFFED_RICE','JAGGERY','BLOUSE_PIECES',
        'BANANA_GELALU','FRUITS','BANGLES',
        'KARPOORAM'
    )
)

-- PASUPU DHANCHADAM
OR
(
    o.occasion_code = 'PASUPU_DHANCHADAM'
    AND p.item_code IN (
        'TURMERIC_BULLETS','KUMKUM','COCONUTS',
        'ROLLU_ROKALI','BETEL_LEAVES',
        'AKSHINTHALU','BLOUSE_PIECES',
        'BANANA','FLOWERS','FRUITS',
        'DECORATIVE_BINDELU',
        'MATCH_BOXES','COTTON_WICKS'
    )
)

-- PANDIRI RAATA
OR
(
    o.occasion_code = 'PANDIRI_RAATA'
    AND p.item_code IN (
        'TURMERIC','KUMKUM','NAVA_DHANYALU',
        'COW_MILK','MANGO_LEAVES',
        'AKSHINTHALU','COCONUTS',
        'BANANA','RICE','JAGGERY',
        'WHITE_THREAD','OIL',
        'MATCH_BOXES','COTTON_WICKS'
    )
)

-- PELLI KODUKU / KUTHURU
OR
(
    o.occasion_code = 'PELLI_KODUKU_KUTHURU'
    AND p.item_code IN (
        'TURMERIC','KUMKUM','CHANDAN_POWDER',
        'TURMERIC_BULLETS','KARPOORAM',
        'BETEL_LEAVES','BANANA_GELALU',
        'WHITE_THREAD','PARANI',
        'OIL','MUTHYALA_THALAMBRALU'
    )
)

-- HALDI
OR
(
    o.occasion_code = 'HALDI'
    AND p.item_code IN (
        'TURMERIC','KUMKUM','UBTAN',
        'KUMKUDUKAYA','CLAY_POTS',
        'MANGO_LEAVES','COW_MILK',
        'CURD','WHITE_THREAD'
    )
)

-- MARRIAGE
OR
(
    o.occasion_code = 'MARRIAGE'
    AND p.item_code IN (
        'TURMERIC','KUMKUM','CHANDAN_POWDER',
        'INCENSE_STICKS','KARPOORAM',
        'RICE','COCONUTS','WHITE_THREAD',
        'DIYAS','MANGO_LEAVES','OIL',
        'COTTON_WICKS','FLOWER_GARLANDS',
        'JEELAKARRA_BELLAM','ADDU_THERA',
        'BASIKAALU'
    )
)

-- SATYANARAYANA POOJA
OR
(
    o.occasion_code = 'SATYANARAYANA_POOJA'
    AND p.item_code IN (
        'TURMERIC','KUMKUM','TURMERIC_BULLETS',
        'CHANDAN_POWDER','BANANA',
        'KARPOORAM','AKSHINTHALU',
        'FLOWERS','MANGO_LEAVES',
        'COCONUTS','INCENSE_STICKS',
        'DIYAS','RICE','JAGGERY',
        'WHITE_THREAD','COTTON_WICKS',
        'OIL','GHEE','HONEY',
        'FRUITS'
    )
)

ON CONFLICT (pooja_item_id, occasion_id) DO NOTHING;
