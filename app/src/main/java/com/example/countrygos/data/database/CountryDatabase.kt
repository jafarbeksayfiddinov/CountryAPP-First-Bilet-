package com.example.countrygos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countrygos.data.dao.CountryDao
import com.example.countrygos.data.dataclazz.Country

@Database(entities = [Country::class], version = 2)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile private var INSTANCE: CountryDatabase? = null

        fun getDatabase(context: Context): CountryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java,
                    "country_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
