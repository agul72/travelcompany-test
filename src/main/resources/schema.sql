CREATE TABLE IF NOT EXISTS company (
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    firstname VARCHAR DEFAULT NULL,
    phone VARCHAR DEFAULT NULL
)