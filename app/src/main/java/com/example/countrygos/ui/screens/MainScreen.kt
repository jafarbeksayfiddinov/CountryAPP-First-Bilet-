package com.example.countrygos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.countrygos.data.viewmodel.CountryViewModel
import com.example.countrygos.ui.util.CountryCard


@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CountryViewModel = viewModel()
) {
    val countries by viewModel.allCountries.observeAsState(emptyList())

    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.Center)
                    .padding(top = 20.dp, bottom = 20.dp),
                text = "Countries",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )

            IconButton(
                onClick = { navController.navigate("favourite_screen") },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
//                    .offset(y = 5.dp) // adjust the value to slightly shift vertically
                    .padding(end = 30.dp)
                    .size(40.dp)

            ) {


                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite Icon",
                    tint = Color.Red,
                    modifier = modifier.fillMaxSize()
//                            modifier = modifier
//                                .padding(end = 40.dp)
//                                .align(Alignment.CenterHorizontally)
//                                .align(Alignment.End)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(countries) { country ->
                CountryCard(
                    country = country,
                    onFavouriteClick = { viewModel.toggleFavourite(it)},
                    onClick = {navController.navigate("description_screen/${country.name}")}
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

}
