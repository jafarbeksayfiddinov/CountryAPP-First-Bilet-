package com.example.countrygos.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.countrygos.data.database.CountryDatabase
import com.example.countrygos.data.dataclazz.Country
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : AndroidViewModel(application) {
    private val db = CountryDatabase.getDatabase(application)
    private val dao = db.countryDao()

    fun insertCountry(country: Country) = viewModelScope.launch {
        dao.insert(country)
    }
    // Expose LiveData properties directly from DAO (assuming dao.getAll() returns LiveData<List<Country>>)
    val allCountries: LiveData<List<Country>> = dao.getAll()

    fun getFavouriteCountries()= liveData {
        emit(dao.getFavourites())
    }
    fun toggleFavourite(country: Country) {
        viewModelScope.launch {
            val updated = country.copy(isFavourite = !country.isFavourite)
            dao.updateCountry(updated)
        }
    }
}