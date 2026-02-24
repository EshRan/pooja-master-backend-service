-- 1️⃣ Add category column with default value 'FESTIVE'
ALTER TABLE occasions ADD COLUMN IF NOT EXISTS category VARCHAR(50) DEFAULT 'FESTIVE';

-- 2️⃣ Update existing marriage-related occasions to 'MARRIAGE' category
UPDATE occasions
SET category = 'MARRIAGE'
WHERE occasion_code IN (
    'ENGAGEMENT',
    'PASUPU_DHANCHADAM',
    'PANDIRI_RAATA',
    'PELLI_KODUKU_KUTHURU',
    'HALDI',
    'MARRIAGE'
);
