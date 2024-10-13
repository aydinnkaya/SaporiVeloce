package com.aydinkaya.saporiveloce.views.home


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.data.entity.Yemekler
import com.aydinkaya.saporiveloce.viewmodel.YemekKayitViewModel
import com.aydinkaya.saporiveloce.viewmodel.FavoriScreenViewModel

@Composable
fun YemekListScreen(
    navController: NavController,
    yemekList: List<Yemekler>,
    yemekKayitViewModel: YemekKayitViewModel,
    favoriScreenViewModel: FavoriScreenViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )  {
        items(yemekList.size) { index ->
            val yemek = yemekList[index]
            Log.d("YemekListScreen", "Yemek kartı oluşturuluyor: ${yemek.yemek_adi}")
            YemekCard(
                yemek = yemek,
                navController = navController,
                yemekKayitViewModel = yemekKayitViewModel,
                favoriScreenViewModel = favoriScreenViewModel
            )
        }
    }
}






/*
import android.util.Log
import androidx.compose.runtime.Composable
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.data.entity.Yemek




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun YemekListScreen(
    navController: NavController,
    yemekList: List<Yemek>,
    viewModel: YemekViewModel = hiltViewModel()
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(yemekList.size) { index ->
            val yemek = yemekList[index]
            Log.d("YemekListScreen", "Yemek kartı oluşturuluyor: ${yemek.yemek_adi}")
            YemekCard(
                yemek = yemek,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}


 */