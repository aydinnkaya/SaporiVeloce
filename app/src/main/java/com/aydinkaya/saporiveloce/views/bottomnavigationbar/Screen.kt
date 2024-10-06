package com.aydinkaya.saporiveloce.views.bottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Cart : Screen("cart", "Cart", Icons.Filled.ShoppingCart)
    data object Profile : Screen("profile", "Profile", Icons.Filled.Person)
}