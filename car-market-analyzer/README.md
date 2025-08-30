# 🚗 Car Market Analyzer

A comprehensive full-stack web application for analyzing car market trends, searching vehicles, and providing detailed market insights.

## ✨ Features

- **🔍 Advanced Car Search**: Filter by make, model, year, price, location, and more
- **📊 Market Analytics**: Interactive dashboard with price trends and market statistics
- **📱 Responsive Design**: Modern UI that works on all devices
- **🔐 User Management**: Registration, authentication, and profile management
- **📈 Real-time Data**: Live market data and price tracking
- **🎨 Beautiful UI**: Modern design with Tailwind CSS and smooth animations

## 🏗️ Architecture

- **Backend**: Java Spring Boot with JPA/Hibernate
- **Frontend**: React.js with Tailwind CSS
- **Database**: PostgreSQL
- **Security**: Spring Security with BCrypt encryption
- **API**: RESTful endpoints with comprehensive documentation

## 🚀 Quick Start

### Prerequisites

- Java 11 or higher
- Node.js 16+ and npm
- PostgreSQL 12+
- Maven 3.6+

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/car-market-analyzer.git
   cd car-market-analyzer/backend
   ```

2. **Database Setup**
   ```bash
   # Create PostgreSQL database
   createdb car_market_analyzer
   
   # Or use psql
   psql -U postgres
   CREATE DATABASE car_market_analyzer;
   ```

3. **Configuration**
   ```bash
   # Copy the template and customize
   cp src/main/resources/application.properties.template src/main/resources/application.properties
   
   # Edit application.properties with your database credentials
   nano src/main/resources/application.properties
   ```

4. **Run the Backend**
   ```bash
   mvn spring-boot:run
   ```
   
   The backend will start on `http://localhost:8081`

### Frontend Setup

1. **Navigate to frontend directory**
   ```bash
   cd ../frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start the application**
   ```bash
   npm start
   ```
   
   The frontend will open at `http://localhost:3000`

## 📚 API Documentation

### Car Endpoints

- `GET /api/cars` - Get all cars
- `POST /api/cars/search` - Search cars with filters
- `GET /api/cars/{id}` - Get car by ID
- `GET /api/cars/make/{make}` - Get cars by make
- `GET /api/cars/make/{make}/model/{model}` - Get cars by make and model

### Analytics Endpoints

- `GET /api/analytics/overview` - Market overview
- `GET /api/analytics/price-trends/{carId}` - Price trends for specific car
- `GET /api/analytics/price-analysis/make/{make}/model/{model}` - Price analysis

### User Endpoints

- `POST /api/users/register` - User registration
- `POST /api/users/login` - User authentication
- `GET /api/users/{id}` - Get user profile
- `PUT /api/users/{id}` - Update user profile

## 🔒 Security Features

- **Password Encryption**: BCrypt hashing for secure password storage
- **CORS Configuration**: Configurable cross-origin resource sharing
- **Input Validation**: Comprehensive request validation
- **SQL Injection Protection**: JPA/Hibernate parameterized queries

## 🛠️ Development

### Backend Development

```bash
# Run with hot reload
mvn spring-boot:run

# Run tests
mvn test

# Build JAR
mvn clean package
```

### Frontend Development

```bash
# Start development server
npm start

# Build for production
npm run build

# Run tests
npm test
```

## 📁 Project Structure

```
car-market-analyzer/
├── backend/
│   ├── src/main/java/com/carmarket/analyzer/
│   │   ├── controller/     # REST controllers
│   │   ├── service/        # Business logic
│   │   ├── repository/     # Data access layer
│   │   ├── model/          # JPA entities
│   │   ├── dto/            # Data transfer objects
│   │   └── config/         # Configuration classes
│   └── src/main/resources/
│       └── application.properties
├── frontend/
│   ├── src/
│   │   ├── components/     # Reusable UI components
│   │   ├── pages/          # Page components
│   │   └── App.jsx         # Main application
│   └── package.json
└── README.md
```

## 🌟 Sample Data

The application automatically loads sample data including:
- **36 cars** across multiple makes and models
- **Various locations** across the United States
- **Price range**: $21,000 - $49,000
- **Years**: 2019-2022
- **Fuel types**: Gasoline, Electric, Hybrid

## 🔧 Configuration

### Environment Variables

Create a `.env` file in the backend directory:

```bash
DB_URL=jdbc:postgresql://localhost:5432/car_market_analyzer
DB_USERNAME=your_username
DB_PASSWORD=your_password
SERVER_PORT=8081
```

### Production Settings

For production deployment:

1. **Update CORS origins** in `application.properties`
2. **Set logging levels** to INFO or WARN
3. **Enable proper authentication** and CSRF protection
4. **Use environment variables** for sensitive data
5. **Configure HTTPS** and proper SSL certificates

## 🚀 Deployment

### Backend Deployment

```bash
# Build JAR
mvn clean package

# Run JAR
java -jar target/car-market-analyzer-1.0-SNAPSHOT.jar
```

### Frontend Deployment

```bash
# Build for production
npm run build

# Deploy the build folder to your web server
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

If you encounter any issues:

1. Check the [Issues](https://github.com/yourusername/car-market-analyzer/issues) page
2. Create a new issue with detailed information
3. Include your environment details and error logs

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- React.js community for the powerful frontend library
- Tailwind CSS for the beautiful styling system
- PostgreSQL for the reliable database

---

**Made with ❤️ for car enthusiasts and market analysts**