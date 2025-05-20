package com.example.countrygos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.countrygos.data.dataclazz.Country
import com.example.countrygos.data.viewmodel.CountryViewModel


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
                    onFavouriteClick = { viewModel.toggleFavourite(it) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

}

@Composable
fun CountryCard(country: Country, onFavouriteClick: (Country) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(modifier = Modifier.padding(top = 20.dp, bottom = 0.dp)) {
            Surface(
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 10.dp, top = 10.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Image(
                    painter = painterResource(id = country.photo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            // Name
            Text(
                text = country.name,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp, top = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onFavouriteClick(country) }) {
                    Icon(
                        imageVector = if (country.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        tint = Color.Red
                    )
                }
            }
        }

        Text(
            text = country.description,
            modifier = Modifier.padding(start = 70.dp, bottom = 10.dp)
        )
    }
}

