import React from 'react';
import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { 
  Car, 
  MapPin, 
  Calendar, 
  Zap, 
  Shield, 
  TrendingUp,
  Star
} from 'lucide-react';

const CarCard = ({ car }) => {
  const formatPrice = (price) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 0,
      maximumFractionDigits: 0,
    }).format(price);
  };

  const formatMileage = (mileage) => {
    return new Intl.NumberFormat('en-US').format(mileage);
  };

  const getFuelTypeColor = (fuelType) => {
    const colors = {
      'Gasoline': 'bg-blue-100 text-blue-800',
      'Diesel': 'bg-gray-100 text-gray-800',
      'Electric': 'bg-green-100 text-green-800',
      'Hybrid': 'bg-purple-100 text-purple-800',
      'Plug-in Hybrid': 'bg-indigo-100 text-indigo-800'
    };
    return colors[fuelType] || 'bg-gray-100 text-gray-800';
  };

  const getTransmissionColor = (transmission) => {
    const colors = {
      'Automatic': 'bg-orange-100 text-orange-800',
      'Manual': 'bg-red-100 text-red-800',
      'CVT': 'bg-teal-100 text-teal-800',
      'Semi-Automatic': 'bg-pink-100 text-pink-800'
    };
    return colors[transmission] || 'bg-gray-100 text-gray-800';
  };

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      whileHover={{ y: -5 }}
      transition={{ duration: 0.3 }}
      className="bg-white rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 overflow-hidden border border-gray-100"
    >
      {/* Header */}
      <div className="bg-gradient-to-r from-blue-600 to-purple-600 p-4 text-white">
        <div className="flex items-center justify-between mb-2">
          <h3 className="text-xl font-bold">{car.make} {car.model}</h3>
          <div className="flex items-center space-x-1">
            {[...Array(5)].map((_, i) => (
              <Star
                key={i}
                className={`w-4 h-4 ${i < car.safetyRating ? 'text-yellow-300 fill-current' : 'text-white/30'}`}
              />
            ))}
          </div>
        </div>
        <div className="flex items-center space-x-4 text-blue-100 text-sm">
          <div className="flex items-center space-x-1">
            <Calendar className="w-4 h-4" />
            <span>{car.year}</span>
          </div>
          <div className="flex items-center space-x-1">
            <MapPin className="w-4 h-4" />
            <span>{car.location}</span>
          </div>
        </div>
      </div>

      {/* Content */}
      <div className="p-6">
        {/* Price */}
        <div className="text-center mb-6">
          <div className="text-3xl font-bold text-gray-900 mb-1">
            {formatPrice(car.price)}
          </div>
          <div className="text-sm text-gray-500">Current Market Price</div>
        </div>

        {/* Specifications */}
        <div className="space-y-3 mb-6">
          <div className="flex items-center justify-between">
            <span className="text-gray-600">Engine:</span>
            <span className="font-medium text-gray-900">{car.engineSpecs}</span>
          </div>
          <div className="flex items-center justify-between">
            <span className="text-gray-600">Mileage:</span>
            <span className="font-medium text-gray-900">{formatMileage(car.mileage)} mi</span>
          </div>
          <div className="flex items-center justify-between">
            <span className="text-gray-600">Safety Rating:</span>
            <span className="font-medium text-gray-900">{car.safetyRating}/5</span>
          </div>
        </div>

        {/* Tags */}
        <div className="flex flex-wrap gap-2 mb-6">
          <span className={`px-3 py-1 rounded-full text-xs font-medium ${getFuelTypeColor(car.fuelType)}`}>
            {car.fuelType}
          </span>
          <span className={`px-3 py-1 rounded-full text-xs font-medium ${getTransmissionColor(car.transmission)}`}>
            {car.transmission}
          </span>
        </div>

        {/* Action Buttons */}
        <div className="flex space-x-3">
          <Link
            to={`/car/${car.id}`}
            className="flex-1 bg-blue-600 text-white py-2 px-4 rounded-lg font-medium hover:bg-blue-700 transition-colors text-center"
          >
            View Details
          </Link>
          <button className="px-4 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors">
            <TrendingUp className="w-4 h-4" />
          </button>
        </div>
      </div>

      {/* Footer */}
      <div className="bg-gray-50 px-6 py-3 border-t border-gray-100">
        <div className="flex items-center justify-between text-sm text-gray-600">
          <div className="flex items-center space-x-1">
            <Zap className="w-4 h-4" />
            <span>Real-time data</span>
          </div>
          <div className="flex items-center space-x-1">
            <Shield className="w-4 h-4" />
            <span>Verified listing</span>
          </div>
        </div>
      </div>
    </motion.div>
  );
};

export default CarCard;
