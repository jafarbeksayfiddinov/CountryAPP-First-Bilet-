package com.example.countrygos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.countrygos.data.viewmodel.CountryViewModel
import com.example.countrygos.ui.util.CountryDescUtil

@Composable
fun DescriptionScreen(navController: NavHostController,
                      countryName: String,
                      viewModel: CountryViewModel = viewModel()
) {
    val countries by viewModel.allCountries.observeAsState(emptyList())
    val country = countries.find { it.name == countryName }

    if (country == null) {
        Text("Country not found", modifier = Modifier.padding(16.dp))
        return
    }

    CountryDescUtil(country)

}