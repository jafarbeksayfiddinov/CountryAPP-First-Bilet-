package com.example.countrygos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.countrygos.data.viewmodel.CountryViewModel
import com.example.countrygos.ui.util.CountryCard

@Composable
fun FavouriteScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CountryViewModel = viewModel()
) {
    val favouriteCountries by viewModel.favouriteCountries.observeAsState(emptyList())

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Favourite Countries",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp, bottom = 20.dp)
        )

        if (favouriteCountries.isEmpty()) {
            Text(
                text = "No favourites yet.",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 100.dp),
                color = Color.Gray,
                fontSize = 16.sp
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(favouriteCountries) { country ->
                    CountryCard(
                        country = country,
                        onFavouriteClick = { viewModel.toggleFavourite(it) },
                        onClick = {navController.navigate("description_screen/${country.name}")}

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
