ALTER TABLE pharmacies
ALTER COLUMN name SET DATA TYPE varchar(255);

ALTER TABLE pharmacies
ALTER COLUMN medicine_link_template SET DATA TYPE varchar(255);

ALTER TABLE medicines
ALTER COLUMN title SET DATA TYPE varchar(255);

ALTER TABLE prices
ADD CONSTRAINT fk_pharmacy_id FOREIGN KEY pharmacy_id REFERENCES pharmacies(id);

ALTER TABLE prices
ADD CONSTRAINT fk_medicine_id FOREIGN KEY medicine_id REFERENCES medicines(id);
