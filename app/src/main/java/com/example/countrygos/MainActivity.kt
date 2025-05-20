package com.example.countrygos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.countrygos.data.dao.CountryDao
import com.example.countrygos.data.database.CountryDatabase
import com.example.countrygos.data.dataclazz.Country
import com.example.countrygos.navigation.AppNavHost
import com.example.countrygos.ui.theme.CountryGosTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryGosTheme {
//                Scaffold(modifier = Modifier.fillMaxSize())
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
        val countryDao = CountryDatabase.getDatabase(this).countryDao()

        lifecycleScope.launch {
            // Check if DB is empty (run in IO dispatcher)
            val count = withContext(Dispatchers.IO) {
                countryDao.getCount() // You should add this function in your DAO
            }

            if (count == 0) {
                // Insert initial data
                withContext(Dispatchers.IO) {
                    val initialCountries = listOf(
                        Country(
                            name = "Uzbekistan",
                            description = "A country in Central Asia.",
                            isFavourite = false,
                            photo = R.drawable.uzb_icon,
                            capital = "Tashkent"
                        ),
                        Country(
                            name = "United States",
                            description = "A country in North America.",
                            isFavourite = false,
                            photo = R.drawable.usa_icon,
                            capital = "Washington"

                        ),
                        Country(
                            name = "France",
                            description = "A country in Europe.",
                            isFavourite = false,
                            photo = R.drawable.france_icon,
                            capital = "Lyon"

                        )
                    )
                    countryDao.insertAll(initialCountries)
                }
            }

            // Observe countries (on main thread)
            countryDao.getAll().observe(this@MainActivity) { countries ->
                Log.d("DB_DEBUG", "Countries in DB: ${countries.size}")
            }
        }
    }
}






