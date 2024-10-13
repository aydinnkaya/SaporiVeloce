package com.aydinkaya.saporiveloce.views.bottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigateScreens(val route: String, val title: String, val icon: ImageVector) {
    object Home : NavigateScreens("home", "Home", Icons.Filled.Home)

    object Checkout : NavigateScreens("sepetsayfa", "Sepetim", Icons.Default.ShoppingCart)

    object Favorites : NavigateScreens("favorisayfa", "Favorilerim", Icons.Default.Favorite)

}