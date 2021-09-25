DROP TABLE IF EXISTS clothes CASCADE;
CREATE TABLE clothes
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    style VARCHAR(255),
    season VARCHAR(255),
    url VARCHAR(500)
);