package com.aydinkaya.saporiveloce.views.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun YemekListScreen(
    navController: NavController,
    viewModel: YemekViewModel = hiltViewModel()
) {
    val yemekList by viewModel.yemekList.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
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
                YemekCard(
                    yemek = yemek,
                    onSepeteEkle = {
                        viewModel.sepeteYemekEkle(
                            SepetYemek(
                                sepet_yemek_id = 0,
                                yemek_adi = yemek.yemek_adi,
                                yemek_resim_adi = yemek.yemek_resim_adi,
                                yemek_fiyat = yemek.yemek_fiyat,
                                yemek_siparis_adet = 1,
                                kullanici_adi = "kasim_adalan"
                            )
                        )
                    },
                    navController = navController
                )
            }
        }
    }
}
