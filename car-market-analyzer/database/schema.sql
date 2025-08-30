CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    location VARCHAR(100),
    price DECIMAL(10, 2),
    engine_specs TEXT,
    fuel_type VARCHAR(50),
    transmission VARCHAR(50),
    mileage INT,
    safety_rating INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE price_history (
    id SERIAL PRIMARY KEY,
    car_id INT REFERENCES cars(id),
    price DECIMAL(10, 2),
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE favorite_searches (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    search_criteria JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);