package com.aydinkaya.saporiveloce.views.bottomnavigationbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.aydinkaya.saporiveloce.viewmodel.FavoriScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.SepetViewModel
import com.aydinkaya.saporiveloce.viewmodel.YemekKayitViewModel
import com.example.graduationproject.views.viewmodel.HomeViewModel


@Composable
fun BottomBarSayfa(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    yemekKayitViewModel: YemekKayitViewModel,
    favoriScreenViewModel: FavoriScreenViewModel,
    sepetViewModel: SepetViewModel
) {
    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top
        ) {
            NavigationGraph(
                navController = navController,
                homeViewModel = homeViewModel,
                yemekKayitViewModel = yemekKayitViewModel,
                favoriScreenViewModel = favoriScreenViewModel,
                sepetViewModel = sepetViewModel
            )
        }
    }
}
