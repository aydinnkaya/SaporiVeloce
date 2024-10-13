package com.aydinkaya.saporiveloce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.aydinkaya.saporiveloce.ui.theme.SaporiVeloceTheme
import com.aydinkaya.saporiveloce.viewmodel.FavoriScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.SepetViewModel
import com.aydinkaya.saporiveloce.viewmodel.YemekKayitViewModel
import com.aydinkaya.saporiveloce.views.bottomnavigationbar.BottomBarSayfa
import com.example.graduationproject.views.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            setContent {
                SaporiVeloceTheme {

                    val navController = rememberNavController()
                    val homeViewModel: HomeViewModel = hiltViewModel()
                    val yemekKayitViewModel: YemekKayitViewModel = hiltViewModel()
                    val sepetViewModel: SepetViewModel = hiltViewModel()
                    val favoriScreenViewModel: FavoriScreenViewModel = hiltViewModel()

                    BottomBarSayfa(
                        navController = navController,
                        homeViewModel = homeViewModel,
                        yemekKayitViewModel = yemekKayitViewModel,
                        sepetViewModel = sepetViewModel,
                        favoriScreenViewModel = favoriScreenViewModel
                    )
                }
            }

            /*
            SaporiVeloceTheme {
               NavigationStack()
            }

             */
        }
    }
}

