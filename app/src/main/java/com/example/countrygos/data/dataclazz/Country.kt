package com.example.countrygos.data.dataclazz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val isFavourite: Boolean,
    val photo: Int,
    val capital:String
)