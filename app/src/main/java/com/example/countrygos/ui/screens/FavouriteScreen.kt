package com.example.countrygos.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun FavouriteScreen(navController: NavHostController, modifier: Modifier=Modifier) {
    Text(
        modifier = modifier.fillMaxSize(),
        text = "Favourite Screen"
    )
}