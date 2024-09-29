package com.aydinkaya.saporiveloce.views.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aydinkaya.saporiveloce.views.productdetailscreen.ProductDetailScreen
import com.aydinkaya.saporiveloce.views.shopping.CartScreen


@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("cart") {
            CartScreen()
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
            val yemekAciklama = backStackEntry.arguments?.getString("yemekAciklama") ?: "" // Yeni eklenen açıklama

            ProductDetailScreen(
                navController = navController,
                yemekId = yemekId,
                yemekAdi = yemekAdi,
                yemekFiyat = yemekFiyat,
                yemekResimAdi = yemekResimAdi,
                yemekAciklama = yemekAciklama,  // Açıklamayı da ekliyoruz
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
    }
}
