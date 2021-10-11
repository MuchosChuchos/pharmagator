ALTER TABLE prices
ADD CONSTRAINT fk_prices_medicines FOREIGN KEY (medicine_id) REFERENCES medicines (id),
ADD CONSTRAINT fk_prices_pharmacies FOREIGN KEY (pharmacy_id) REFERENCES pharmacies (id);