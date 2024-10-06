package com.aydinkaya.saporiveloce.views.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aydinkaya.saporiveloce.data.entity.Yemek
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.foundation.clickable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun YemekCard(
    yemek: Yemek,
    navController: NavController,
    viewModel: YemekViewModel
) {
    val cardBackgroundColor = Color.White
    val yemekAciklama = viewModel.yemekAciklamasiniGetir(yemek.yemek_id)
    val snackbarHostState = remember { SnackbarHostState() }

    Card(
        modifier = Modifier
            .width(240.dp)
            .padding(8.dp)
            .clickable {
                navController.navigate("productDetail/${yemek.yemek_id}/${yemek.yemek_adi}/${yemek.yemek_fiyat}/${yemek.yemek_resim_adi}/$yemekAciklama")
            },
        colors = CardDefaults.cardColors(cardBackgroundColor),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"),
                    contentDescription = yemek.yemek_adi,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(180.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(8.dp)
                        .clickable { },
                    tint = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = yemek.yemek_adi,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${yemek.yemek_fiyat}₺",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFFFF9800)
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val sepetYemek = SepetYemek(
                        sepet_yemek_id = 0,
                        yemek_adi = yemek.yemek_adi,
                        yemek_resim_adi = yemek.yemek_resim_adi,
                        yemek_fiyat = yemek.yemek_fiyat,
                        yemek_siparis_adet = 1,
                        kullanici_adi = "current_user"
                    )
                    viewModel.sepeteYemekEkle(sepetYemek) // Pass sepetYemek to the ViewModel

                    CoroutineScope(Dispatchers.Main).launch {
                        snackbarHostState.showSnackbar(
                            message = "${yemek.yemek_adi} added to cart",
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add to cart", color = Color.Black)
            }
        }
    }
    SnackbarHost(hostState = snackbarHostState)
}
