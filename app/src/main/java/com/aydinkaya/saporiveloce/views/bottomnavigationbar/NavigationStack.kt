package com.aydinkaya.saporiveloce.views.bottomnavigationbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aydinkaya.saporiveloce.viewmodel.HomeScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel
import com.aydinkaya.saporiveloce.views.home.HomeScreen
import com.aydinkaya.saporiveloce.views.home.ProfileScreen
import com.aydinkaya.saporiveloce.views.productdetailscreen.ProductDetailScreen
import com.aydinkaya.saporiveloce.views.shopping.CartScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { CustomBottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                val yemekViewModel = hiltViewModel<YemekViewModel>()
                HomeScreen(navController = navController, viewModel = homeScreenViewModel, yemekViewModel = yemekViewModel)
            }
            composable("cart") {
                val viewModel = hiltViewModel<YemekViewModel>()
                CartScreen(viewModel = viewModel, navController = navController)
            }
            composable("profile") {
                ProfileScreen()
            }

            composable(
                "productDetail/{yemekId}/{yemekAdi}/{yemekFiyat}/{yemekResimAdi}/{yemekAciklama}",
                arguments = listOf(
                    navArgument("yemekId") { type = NavType.IntType },
                    navArgument("yemekAdi") { type = NavType.StringType },
                    navArgument("yemekFiyat") { type = NavType.StringType },
                    navArgument("yemekResimAdi") { type = NavType.StringType },
                    navArgument("yemekAciklama") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val yemekId = backStackEntry.arguments?.getInt("yemekId") ?: 0
                val yemekAdi = backStackEntry.arguments?.getString("yemekAdi") ?: ""
                val yemekFiyat = backStackEntry.arguments?.getString("yemekFiyat") ?: ""
                val yemekResimAdi = backStackEntry.arguments?.getString("yemekResimAdi") ?: ""
                val yemekAciklama = backStackEntry.arguments?.getString("yemekAciklama") ?: ""

                ProductDetailScreen(
                    navController = navController,
                    yemekId = yemekId,
                    yemekAdi = yemekAdi,
                    yemekFiyat = yemekFiyat,
                    yemekResimAdi = yemekResimAdi,
                    yemekAciklama = yemekAciklama,
                    onBackPress = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
