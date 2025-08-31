import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { 
  User, 
  Mail, 
  Lock, 
  Heart, 
  Search, 
  Settings, 
  Edit3, 
  Save,
  X,
  Plus,
  Trash2,
  Calendar
} from 'lucide-react';
import axios from 'axios';

const UserProfilePage = () => {
  const [user, setUser] = useState({
    username: 'john_doe',
    email: 'john@example.com',
    createdAt: '2024-01-15'
  });
  const [isEditing, setIsEditing] = useState(false);
  const [editForm, setEditForm] = useState({
    username: '',
    email: ''
  });
  const [favoriteSearches, setFavoriteSearches] = useState([
    {
      id: 1,
      name: 'Toyota Camry 2020-2022',
      criteria: { make: 'Toyota', model: 'Camry', yearFrom: 2020, yearTo: 2022 },
      createdAt: '2024-01-20'
    },
    {
      id: 2,
      name: 'Electric Cars Under $50K',
      criteria: { fuelType: 'Electric', priceTo: 50000 },
      createdAt: '2024-01-25'
    }
  ]);
  const [newSearch, setNewSearch] = useState({
    name: '',
    make: '',
    model: '',
    yearFrom: '',
    yearTo: '',
    priceFrom: '',
    priceTo: '',
    location: ''
  });
  const [showNewSearchForm, setShowNewSearchForm] = useState(false);

  useEffect(() => {
    setEditForm({
      username: user.username,
      email: user.email
    });
  }, [user]);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = async () => {
    try {
      // In a real app, you'd make an API call here
      setUser({
        ...user,
        username: editForm.username,
        email: editForm.email
      });
      setIsEditing(false);
    } catch (error) {
      console.error('Error updating profile:', error);
    }
  };

  const handleCancel = () => {
    setEditForm({
      username: user.username,
      email: user.email
    });
    setIsEditing(false);
  };

  const handleInputChange = (field, value) => {
    setEditForm(prev => ({ ...prev, [field]: value }));
  };

  const handleNewSearchInputChange = (field, value) => {
    setNewSearch(prev => ({ ...prev, [field]: value }));
  };

  const addFavoriteSearch = () => {
    if (newSearch.name.trim()) {
      const search = {
        id: Date.now(),
        name: newSearch.name,
        criteria: { ...newSearch },
        createdAt: new Date().toISOString().split('T')[0]
      };
      setFavoriteSearches(prev => [search, ...prev]);
      setNewSearch({
        name: '',
        make: '',
        model: '',
        yearFrom: '',
        yearTo: '',
        priceFrom: '',
        priceTo: '',
        location: ''
      });
      setShowNewSearchForm(false);
    }
  };

  const removeFavoriteSearch = (id) => {
    setFavoriteSearches(prev => prev.filter(search => search.id !== id));
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    });
  };

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
            User Profile
          </motion.h1>
          <p className="text-gray-600">
            Manage your account settings and preferences
          </p>
        </div>
      </div>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Main Content */}
          <div className="lg:col-span-2">
            {/* Profile Information */}
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              className="bg-white rounded-xl shadow-lg p-8 mb-8"
            >
              <div className="flex items-center justify-between mb-6">
                <h2 className="text-2xl font-bold text-gray-900">Profile Information</h2>
                {!isEditing ? (
                  <button
                    onClick={handleEdit}
                    className="flex items-center space-x-2 px-4 py-2 text-primary-600 hover:bg-primary-50 rounded-lg transition-colors"
                  >
                    <Edit3 className="w-4 h-4" />
                    <span>Edit</span>
                  </button>
                ) : (
                  <div className="flex space-x-2">
                    <button
                      onClick={handleSave}
                      className="flex items-center space-x-2 px-4 py-2 bg-primary-600 text-white rounded-lg hover:bg-primary-700 transition-colors"
                    >
                      <Save className="w-4 h-4" />
                      <span>Save</span>
                    </button>
                    <button
                      onClick={handleCancel}
                      className="flex items-center space-x-2 px-4 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors"
                    >
                      <X className="w-4 h-4" />
                      <span>Cancel</span>
                    </button>
                  </div>
                )}
              </div>

              <div className="space-y-6">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Username
                  </label>
                  {isEditing ? (
                    <input
                      type="text"
                      value={editForm.username}
                      onChange={(e) => handleInputChange('username', e.target.value)}
                      className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                    />
                  ) : (
                    <div className="flex items-center space-x-3 p-3 bg-gray-50 rounded-lg">
                      <User className="w-5 h-5 text-gray-400" />
                      <span className="text-gray-900">{user.username}</span>
                    </div>
                  )}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Email
                  </label>
                  {isEditing ? (
                    <input
                      type="email"
                      value={editForm.email}
                      onChange={(e) => handleInputChange('email', e.target.value)}
                      className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                    />
                  ) : (
                    <div className="flex items-center space-x-3 p-3 bg-gray-50 rounded-lg">
                      <Mail className="w-5 h-5 text-gray-400" />
                      <span className="text-gray-900">{user.email}</span>
                    </div>
                  )}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Member Since
                  </label>
                  <div className="flex items-center space-x-3 p-3 bg-gray-50 rounded-lg">
                    <Calendar className="w-5 h-5 text-gray-400" />
                    <span className="text-gray-900">{formatDate(user.createdAt)}</span>
                  </div>
                </div>
              </div>
            </motion.div>

            {/* Favorite Searches */}
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.1 }}
              className="bg-white rounded-xl shadow-lg p-8"
            >
              <div className="flex items-center justify-between mb-6">
                <h2 className="text-2xl font-bold text-gray-900">Favorite Searches</h2>
                                  <button
                    onClick={() => setShowNewSearchForm(!showNewSearchForm)}
                    className="flex items-center space-x-2 px-4 py-2 bg-primary-600 text-white rounded-lg hover:bg-primary-700 transition-colors"
                  >
                    <Plus className="w-4 h-4" />
                    <span>Add Search</span>
                  </button>
              </div>

              {/* New Search Form */}
              {showNewSearchForm && (
                <motion.div
                  initial={{ opacity: 0, height: 0 }}
                  animate={{ opacity: 1, height: 'auto' }}
                  exit={{ opacity: 0, height: 0 }}
                  className="bg-gray-50 rounded-lg p-6 mb-6"
                >
                  <h3 className="text-lg font-semibold text-gray-900 mb-4">New Favorite Search</h3>
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">Search Name</label>
                      <input
                        type="text"
                        value={newSearch.name}
                        onChange={(e) => handleNewSearchInputChange('name', e.target.value)}
                        placeholder="e.g., Toyota Camry 2020-2022"
                        className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                      />
                    </div>
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">Make</label>
                      <input
                        type="text"
                        value={newSearch.make}
                        onChange={(e) => handleNewSearchInputChange('make', e.target.value)}
                        placeholder="e.g., Toyota"
                        className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                      />
                    </div>
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">Model</label>
                      <input
                        type="text"
                        value={newSearch.model}
                        onChange={(e) => handleNewSearchInputChange('model', e.target.value)}
                        placeholder="e.g., Camry"
                        className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                      />
                    </div>
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">Location</label>
                      <input
                        type="text"
                        value={newSearch.location}
                        onChange={(e) => handleNewSearchInputChange('location', e.target.value)}
                        placeholder="e.g., New York"
                        className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                      />
                    </div>
                  </div>
                  <div className="flex space-x-3">
                    <button
                      onClick={addFavoriteSearch}
                      className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-colors"
                    >
                      Save Search
                    </button>
                    <button
                      onClick={() => setShowNewSearchForm(false)}
                      className="px-4 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors"
                    >
                      Cancel
                    </button>
                  </div>
                </motion.div>
              )}

              {/* Favorite Searches List */}
              <div className="space-y-4">
                {favoriteSearches.map((search) => (
                  <div key={search.id} className="flex items-center justify-between p-4 border border-gray-200 rounded-lg hover:border-primary-300 transition-colors">
                    <div className="flex-1">
                      <div className="flex items-center space-x-3 mb-2">
                        <Search className="w-5 h-5 text-primary-600" />
                        <h4 className="font-medium text-gray-900">{search.name}</h4>
                      </div>
                      <div className="text-sm text-gray-600">
                        Created: {formatDate(search.createdAt)}
                      </div>
                    </div>
                    <div className="flex space-x-2">
                                              <button className="p-2 text-primary-600 hover:bg-primary-50 rounded-lg transition-colors">
                        <Search className="w-4 h-4" />
                      </button>
                      <button
                        onClick={() => removeFavoriteSearch(search.id)}
                        className="p-2 text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                      >
                        <Trash2 className="w-4 h-4" />
                      </button>
                    </div>
                  </div>
                ))}
              </div>
            </motion.div>
          </div>

          {/* Sidebar */}
          <div className="lg:col-span-1">
            {/* Account Actions */}
            <motion.div
              initial={{ opacity: 0, x: 20 }}
              animate={{ opacity: 1, x: 0 }}
              transition={{ delay: 0.2 }}
              className="bg-white rounded-xl shadow-lg p-6 mb-6"
            >
              <h3 className="text-lg font-semibold text-gray-900 mb-4">Account Actions</h3>
              <div className="space-y-3">
                <button className="w-full flex items-center justify-center space-x-2 px-4 py-3 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors">
                  <Lock className="w-4 h-4" />
                  <span>Change Password</span>
                </button>
                <button className="w-full flex items-center justify-center space-x-2 px-4 py-3 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors">
                  <Settings className="w-4 h-4" />
                  <span>Preferences</span>
                </button>
              </div>
            </motion.div>

            {/* Quick Stats */}
            <motion.div
              initial={{ opacity: 0, x: 20 }}
              animate={{ opacity: 1, x: 0 }}
              transition={{ delay: 0.3 }}
              className="bg-white rounded-xl shadow-lg p-6"
            >
              <h3 className="text-lg font-semibold text-gray-900 mb-4">Quick Stats</h3>
              <div className="space-y-4">
                <div className="flex items-center justify-between">
                  <span className="text-gray-600">Favorite Searches</span>
                  <span className="font-semibold text-gray-900">{favoriteSearches.length}</span>
                </div>
                <div className="flex items-center justify-between">
                  <span className="text-gray-600">Member Since</span>
                  <span className="font-semibold text-gray-900">{formatDate(user.createdAt)}</span>
                </div>
              </div>
            </motion.div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserProfilePage;
