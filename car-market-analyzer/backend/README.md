# Car Market Analyzer - Backend

## Overview
This is the backend service for the Car Market Analyzer application, built with Spring Boot. It provides a comprehensive REST API for car search, market analysis, user management, and price tracking.

## Features

### 🚗 Car Management
- **Car Search**: Advanced search with multiple criteria (make, model, year, location, price range, etc.)
- **Car CRUD**: Create, read, update, and delete car listings
- **Price Tracking**: Automatic price history tracking for market analysis
- **Specifications**: Detailed vehicle information including engine specs, fuel type, transmission, mileage, and safety ratings

### 📊 Analytics & Market Intelligence
- **Market Overview**: Total cars, average prices, price ranges, popular makes and locations
- **Price Analysis**: Average prices by make/model/year, price trends over time
- **Market Insights**: Fuel type distribution, transmission analysis, safety rating statistics
- **Price Trends**: Historical price tracking with volatility analysis

### 👥 User Management
- **User Registration**: Secure user account creation
- **Authentication**: Login/logout functionality
- **Profile Management**: Update user information and passwords
- **Security**: BCrypt password encryption

## Technical Stack

- **Framework**: Spring Boot 2.5.4
- **Database**: PostgreSQL with JPA/Hibernate
- **Security**: Spring Security with BCrypt
- **Build Tool**: Maven
- **Java Version**: 11+

## Project Structure

```
src/main/java/com/carmarket/analyzer/
├── config/          # Configuration classes
├── controller/      # REST API controllers
├── dto/            # Data Transfer Objects
├── model/          # JPA entities
├── repository/     # Data access layer
├── service/        # Business logic layer
└── CarMarketAnalyzerApplication.java
```

## API Endpoints

### Car Management
- `POST /api/cars/search` - Search cars by criteria
- `GET /api/cars` - Get all cars
- `GET /api/cars/{id}` - Get car by ID
- `GET /api/cars/make/{make}` - Get cars by make
- `GET /api/cars/make/{make}/model/{model}` - Get cars by make and model
- `GET /api/cars/location/{location}` - Get cars by location
- `POST /api/cars` - Create new car
- `PUT /api/cars/{id}/price` - Update car price

### Analytics
- `GET /api/analytics/overview` - Market overview statistics
- `GET /api/analytics/price-analysis/make/{make}/model/{model}` - Price analysis for specific make/model
- `GET /api/analytics/price-trends/{carId}` - Price trends for specific car
- `GET /api/analytics/insights` - Market insights and trends
- `GET /api/analytics/popular-makes` - Most popular car makes
- `GET /api/analytics/popular-locations` - Most popular locations
- `GET /api/analytics/price-range` - Price range statistics

### User Management
- `POST /api/users/register` - User registration
- `POST /api/users/login` - User authentication
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users` - Get all users
- `PUT /api/users/{id}` - Update user information
- `PUT /api/users/{id}/password` - Update user password
- `DELETE /api/users/{id}` - Delete user
- `GET /api/users/check-username/{username}` - Check if username exists
- `GET /api/users/check-email/{email}` - Check if email exists

## Database Schema

### Cars Table
- `id` (Primary Key)
- `make` (Car manufacturer)
- `model` (Car model)
- `year` (Manufacturing year)
- `location` (Geographic location)
- `price` (Current price)
- `engine_specs` (Engine specifications)
- `fuel_type` (Fuel type)
- `transmission` (Transmission type)
- `mileage` (Vehicle mileage)
- `safety_rating` (Safety rating)
- `created_at` (Timestamp)

### Users Table
- `id` (Primary Key)
- `username` (Unique username)
- `email` (Unique email)
- `password` (Encrypted password)
- `created_at` (Timestamp)

### Price History Table
- `id` (Primary Key)
- `car_id` (Foreign key to cars)
- `price` (Historical price)
- `recorded_at` (Timestamp)

### Favorite Searches Table
- `id` (Primary Key)
- `user_id` (Foreign key to users)
- `search_criteria` (JSON search parameters)
- `created_at` (Timestamp)

## Setup Instructions

### Prerequisites
1. Java 11 or higher
2. Maven 3.6+
3. PostgreSQL 12+

### Database Setup
1. Create a PostgreSQL database:
   ```sql
   CREATE DATABASE car_market_analyzer;
   ```

2. Update `application.properties` with your database credentials:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### Running the Application
1. Navigate to the backend directory:
   ```bash
   cd car-market-analyzer/backend
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. The API will be available at `http://localhost:8080`

### Sample Data
The application automatically loads sample car data on first startup, including:
- Toyota Camry, Honda Accord, Ford F-150
- Chevrolet Silverado, BMW 3 Series, Mercedes-Benz C-Class
- Tesla Model 3, Nissan Altima, Hyundai Sonata

## API Usage Examples

### Search for Cars
```bash
curl -X POST http://localhost:8080/api/cars/search \
  -H "Content-Type: application/json" \
  -d '{
    "make": "Toyota",
    "model": "Camry",
    "yearFrom": 2020,
    "priceTo": 30000
  }'
```

### Get Market Overview
```bash
curl http://localhost:8080/api/analytics/overview
```

### Register User
```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "password123"
  }'
```

## Development

### Adding New Features
1. Create entity classes in the `model` package
2. Create repository interfaces in the `repository` package
3. Implement business logic in the `service` package
4. Create REST endpoints in the `controller` package
5. Add DTOs if needed in the `dto` package

### Testing
- Use Postman or similar tool to test API endpoints
- All endpoints support CORS for frontend integration
- Sample data is automatically loaded for testing

## Security Features
- BCrypt password encryption
- CORS configuration for frontend integration
- Input validation and error handling
- Secure password storage

## Monitoring
- Health check endpoint: `/actuator/health`
- Metrics endpoint: `/actuator/metrics`
- Info endpoint: `/actuator/info`

## Future Enhancements
- JWT token authentication
- Role-based access control
- Rate limiting
- API documentation with Swagger
- Integration with external car APIs (CarsXE)
- Real-time price alerts
- Advanced analytics and machine learning