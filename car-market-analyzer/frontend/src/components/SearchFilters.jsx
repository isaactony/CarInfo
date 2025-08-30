import React from 'react';
import { motion } from 'framer-motion';
import { X } from 'lucide-react';

const SearchFilters = ({ filters, onFilterChange }) => {
  const handleInputChange = (field, value) => {
    onFilterChange({ ...filters, [field]: value });
  };

  const clearFilters = () => {
    const clearedFilters = {
      make: '',
      model: '',
      yearFrom: '',
      yearTo: '',
      location: '',
      priceFrom: '',
      priceTo: '',
      fuelType: '',
      transmission: ''
    };
    onFilterChange(clearedFilters);
  };

  const hasActiveFilters = Object.values(filters).some(value => value !== '');

  return (
    <div className="space-y-6">
      {/* Clear Filters */}
      {hasActiveFilters && (
        <motion.button
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          onClick={clearFilters}
          className="w-full flex items-center justify-center space-x-2 px-4 py-2 text-sm text-gray-600 hover:text-red-600 hover:bg-red-50 rounded-lg transition-colors"
        >
          <X className="w-4 h-4" />
          <span>Clear All Filters</span>
        </motion.button>
      )}

      {/* Make */}
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Make
        </label>
        <input
          type="text"
          value={filters.make}
          onChange={(e) => handleInputChange('make', e.target.value)}
          placeholder="e.g., Toyota"
          className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
        />
      </div>

      {/* Model */}
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Model
        </label>
        <input
          type="text"
          value={filters.model}
          onChange={(e) => handleInputChange('model', e.target.value)}
          placeholder="e.g., Camry"
          className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
        />
      </div>

      {/* Year Range */}
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Year Range
        </label>
        <div className="grid grid-cols-2 gap-3">
          <input
            type="number"
            value={filters.yearFrom}
            onChange={(e) => handleInputChange('yearFrom', e.target.value)}
            placeholder="From"
            min="1990"
            max="2024"
            className="px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
          />
          <input
            type="number"
            value={filters.yearTo}
            onChange={(e) => handleInputChange('yearTo', e.target.value)}
            placeholder="To"
            min="1990"
            max="2024"
            className="px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
          />
        </div>
      </div>

      {/* Location */}
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Location
        </label>
        <input
          type="text"
          value={filters.location}
          onChange={(e) => handleInputChange('location', e.target.value)}
          placeholder="e.g., New York"
          className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
        />
      </div>

      {/* Price Range */}
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Price Range ($)
        </label>
        <div className="grid grid-cols-2 gap-3">
          <input
            type="number"
            value={filters.priceFrom}
            onChange={(e) => handleInputChange('priceFrom', e.target.value)}
            placeholder="Min"
            min="0"
            className="px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
          />
          <input
            type="number"
            value={filters.priceTo}
            onChange={(e) => handleInputChange('priceTo', e.target.value)}
            placeholder="Max"
            min="0"
            className="px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
          />
        </div>
      </div>

      {/* Fuel Type */}
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Fuel Type
        </label>
        <select
          value={filters.fuelType}
          onChange={(e) => handleInputChange('fuelType', e.target.value)}
          className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
        >
          <option value="">Any Fuel Type</option>
          <option value="Gasoline">Gasoline</option>
          <option value="Diesel">Diesel</option>
          <option value="Electric">Electric</option>
          <option value="Hybrid">Hybrid</option>
          <option value="Plug-in Hybrid">Plug-in Hybrid</option>
        </select>
      </div>

      {/* Transmission */}
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Transmission
        </label>
        <select
          value={filters.transmission}
          onChange={(e) => handleInputChange('transmission', e.target.value)}
          className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 transition-colors"
        >
          <option value="">Any Transmission</option>
          <option value="Automatic">Automatic</option>
          <option value="Manual">Manual</option>
          <option value="CVT">CVT</option>
          <option value="Semi-Automatic">Semi-Automatic</option>
        </select>
      </div>

      {/* Quick Filter Buttons */}
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Quick Filters
        </label>
        <div className="space-y-2">
          <button
            onClick={() => onFilterChange({ ...filters, priceFrom: '0', priceTo: '25000' })}
            className="w-full text-left px-3 py-2 text-sm text-primary-600 hover:bg-primary-50 rounded-lg transition-colors"
          >
            Under $25K
          </button>
          <button
            onClick={() => onFilterChange({ ...filters, priceFrom: '25000', priceTo: '50000' })}
            className="w-full text-left px-3 py-2 text-sm text-primary-600 hover:bg-primary-50 rounded-lg transition-colors"
          >
            $25K - $50K
          </button>
          <button
            onClick={() => onFilterChange({ ...filters, priceFrom: '50000', priceTo: '100000' })}
            className="w-full text-left px-3 py-2 text-sm text-primary-600 hover:bg-primary-50 rounded-lg transition-colors"
          >
            $50K - $100K
          </button>
          <button
            onClick={() => onFilterChange({ ...filters, priceFrom: '100000', priceTo: '' })}
            className="w-full text-left px-3 py-2 text-sm text-primary-600 hover:bg-primary-50 rounded-lg transition-colors"
          >
            Over $100K
          </button>
        </div>
      </div>
    </div>
  );
};

export default SearchFilters;
