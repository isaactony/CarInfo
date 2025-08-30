import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { Search, Filter, Car, MapPin, DollarSign, Calendar, Zap } from 'lucide-react';
import axios from 'axios';
import CarCard from '../components/CarCard';
import SearchFilters from '../components/SearchFilters';

const SearchPage = () => {
  const [cars, setCars] = useState([]);
  const [loading, setLoading] = useState(false);
  const [filters, setFilters] = useState({
    make: '',
    model: '',
    yearFrom: '',
    yearTo: '',
    location: '',
    priceFrom: '',
    priceTo: '',
    fuelType: '',
    transmission: ''
  });
  const [showFilters, setShowFilters] = useState(false);

  const searchCars = async (searchFilters = filters) => {
    setLoading(true);
    try {
      // Use real backend endpoint for search
      const response = await axios.post('http://localhost:8081/api/cars/search', searchFilters);
      setCars(response.data);
    } catch (error) {
      console.error('Error searching cars:', error);
      // If search fails, try to get all cars as fallback
      try {
        const allCarsResponse = await axios.get('http://localhost:8081/api/cars');
        setCars(allCarsResponse.data);
      } catch (fallbackError) {
        console.error('Fallback also failed:', fallbackError);
        setCars([]);
      }
    } finally {
      setLoading(false);
    }
  };

  // Load initial cars on page load
  useEffect(() => {
    const loadInitialCars = async () => {
      try {
        const response = await axios.get('http://localhost:8081/api/cars');
        setCars(response.data);
      } catch (error) {
        console.error('Error loading initial cars:', error);
        setCars([]);
      }
    };
    
    loadInitialCars();
  }, []);

  const handleFilterChange = (newFilters) => {
    setFilters(newFilters);
    searchCars(newFilters);
  };

  const handleQuickSearch = (make, model) => {
    const newFilters = { ...filters, make, model };
    setFilters(newFilters);
    searchCars(newFilters);
  };

  const popularSearches = [
    { make: 'Toyota', model: 'Camry', label: 'Toyota Camry' },
    { make: 'Honda', model: 'Accord', label: 'Honda Accord' },
    { make: 'Ford', model: 'F-150', label: 'Ford F-150' },
    { make: 'Tesla', model: 'Model 3', label: 'Tesla Model 3' },
    { make: 'BMW', model: '3 Series', label: 'BMW 3 Series' },
    { make: 'Mercedes-Benz', model: 'C-Class', label: 'Mercedes C-Class' }
  ];

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-white border-b border-gray-200">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
          <motion.h1
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            className="text-3xl font-bold text-gray-900 mb-2"
          >
            Find Your Perfect Car
          </motion.h1>
          <p className="text-gray-600">
            Search through thousands of cars with advanced filtering options
          </p>
        </div>
      </div>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-4 gap-8">
          {/* Filters Sidebar */}
          <div className="lg:col-span-1">
            <div className="sticky top-24">
              <div className="bg-white rounded-xl shadow-lg p-6">
                <div className="flex items-center justify-between mb-6">
                  <h3 className="text-lg font-semibold text-gray-900">Filters</h3>
                  <button
                    onClick={() => setShowFilters(!showFilters)}
                    className="lg:hidden p-2 rounded-lg hover:bg-gray-100"
                  >
                    <Filter className="w-5 h-5" />
                  </button>
                </div>
                
                <div className={`lg:block ${showFilters ? 'block' : 'hidden'}`}>
                  <SearchFilters
                    filters={filters}
                    onFilterChange={handleFilterChange}
                  />
                </div>
              </div>
            </div>
          </div>

          {/* Main Content */}
          <div className="lg:col-span-3">
            {/* Quick Search */}
            <div className="bg-white rounded-xl shadow-lg p-6 mb-6">
              <h3 className="text-lg font-semibold text-gray-900 mb-4">Popular Searches</h3>
              <div className="flex flex-wrap gap-3">
                {popularSearches.map((search) => (
                  <button
                    key={search.label}
                    onClick={() => handleQuickSearch(search.make, search.model)}
                    className="px-4 py-2 bg-primary-50 text-primary-700 rounded-lg hover:bg-primary-100 transition-colors text-sm font-medium"
                  >
                    {search.label}
                  </button>
                ))}
              </div>
            </div>

            {/* Search Results */}
            <div className="bg-white rounded-xl shadow-lg p-6">
              <div className="flex items-center justify-between mb-6">
                <h3 className="text-lg font-semibold text-gray-900">
                  Search Results ({cars.length} cars)
                </h3>
                <div className="flex items-center space-x-2 text-sm text-gray-500">
                  <Zap className="w-4 h-4" />
                  <span>Real-time data from backend</span>
                </div>
              </div>

              {loading ? (
                <div className="flex items-center justify-center py-12">
                  <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
                </div>
              ) : cars.length === 0 ? (
                <div className="text-center py-12">
                  <Car className="w-16 h-16 text-gray-300 mx-auto mb-4" />
                  <h3 className="text-lg font-medium text-gray-900 mb-2">No cars found</h3>
                  <p className="text-gray-500">Try adjusting your search criteria</p>
                </div>
              ) : (
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                  {cars.map((car) => (
                    <CarCard key={car.id} car={car} />
                  ))}
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchPage;
