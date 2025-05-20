package com.example.countrygos.navigation

sealed class NavigationItem(val route: String) {
    object MainScreen : NavigationItem("main_screen")
    object DescriptionScreen : NavigationItem("description_screen")
    object FavouriteScreen : NavigationItem("favourite_screen")
}