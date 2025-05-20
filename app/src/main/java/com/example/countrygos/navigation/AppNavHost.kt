package com.example.countrygos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.countrygos.ui.screens.MainScreen
import androidx.navigation.compose.composable
import com.example.countrygos.ui.screens.DescriptionScreen
import com.example.countrygos.ui.screens.FavouriteScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.MainScreen.route,
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {



        composable(NavigationItem.MainScreen.route) {
            MainScreen(navController)
        }
        composable(NavigationItem.FavouriteScreen.route){
            FavouriteScreen(navController)
        }
        composable(NavigationItem.DescriptionScreen.route+"/{countryName}") { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""
            DescriptionScreen(navController = navController, countryName = countryName)
        }

    }
}