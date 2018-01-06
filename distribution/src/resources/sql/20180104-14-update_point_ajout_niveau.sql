ALTER TABLE point ADD COLUMN level integer NOT NULL DEFAULT 1;
ALTER TABLE point ADD COLUMN point_to_next_level bigint NOT NULL DEFAULT 100;