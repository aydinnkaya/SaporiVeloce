package com.aydinkaya.saporiveloce.views.bottomnavigationbar

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.aydinkaya.saporiveloce.viewmodel.FavoriScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.HomeScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.SepetViewModel
import com.aydinkaya.saporiveloce.viewmodel.YemekKayitViewModel
import com.aydinkaya.saporiveloce.views.favorites.FavorilerScreen
import com.aydinkaya.saporiveloce.views.home.HomeScreen
import com.aydinkaya.saporiveloce.views.productdetailscreen.ProductDetailScreen
import com.aydinkaya.saporiveloce.views.profile.ProfileScreen
import com.aydinkaya.saporiveloce.views.screen.pages.SiparislerimSayfa
import com.aydinkaya.saporiveloce.views.shopping.CheckoutScreen
import com.aydinkaya.saporiveloce.views.shopping.OrderDetailsScreen
import com.example.graduationproject.views.viewmodel.HomeViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    yemekKayitViewModel: YemekKayitViewModel,
    sepetViewModel: SepetViewModel,
    favoriScreenViewModel: FavoriScreenViewModel
) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(
                navController = navController,
                viewModel = homeScreenViewModel,
                homeViewModel = homeViewModel,
                yemekKayitViewModel = yemekKayitViewModel,
                favoriScreenViewModel = favoriScreenViewModel
            )
        }

        composable("CheckoutScreen") {
            CheckoutScreen(navController, sepetViewModel)
        }

        composable(
            route = "productDetail/{yemekId}/{yemekAdi}/{yemekFiyat}/{yemekResimAdi}",
            arguments = listOf(
                navArgument("yemekId") { type = NavType.IntType },
                navArgument("yemekAdi") { type = NavType.StringType },
                navArgument("yemekFiyat") { type = NavType.IntType },
                navArgument("yemekResimAdi") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ProductDetailScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                yemekId = backStackEntry.arguments?.getInt("yemekId") ?: 0,
                yemekAdi = backStackEntry.arguments?.getString("yemekAdi") ?: "",
                yemekFiyati = backStackEntry.arguments?.getInt("yemekFiyat") ?: 0,
                yemekResimAdi = backStackEntry.arguments?.getString("yemekResimAdi") ?: "",
                yemekKayitViewModel = hiltViewModel(),
                onBackPress = { navController.popBackStack() }
            )
        }

        composable("favorisayfa") {
            FavorilerScreen(navController, favoriScreenViewModel, yemekKayitViewModel,onBackPress = { navController.popBackStack() })
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }

        composable("siparislerimsayfa") {
            SiparislerimSayfa(navController)
        }


        composable(
            "orderDetailsScreen/{totalPrice}",
            arguments = listOf(
                navArgument("totalPrice") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val totalPrice = backStackEntry.arguments?.getString("totalPrice") ?: ""

            OrderDetailsScreen(
                navController = navController,
                totalPrice = totalPrice
            )
        }



    }
}
