package com.example.countrygos.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.countrygos.data.dataclazz.Country


@Dao
interface CountryDao {
    @Insert
    suspend fun insert(country: Country)

    @Query("SELECT * FROM countries")
    fun getAll(): LiveData<List<Country>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<Country>)

    @Update
    suspend fun updateCountry(country: Country)

    @Delete
    suspend fun delete(country: Country)

    @Query("SELECT * FROM countries WHERE isFavourite")
    fun getFavourites(): LiveData<List<Country>>

    @Query("SELECT COUNT(*) FROM countries")
    fun getCount(): Int
}