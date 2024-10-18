package com.aydinkaya.saporiveloce.views.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aydinkaya.saporiveloce.R
import com.aydinkaya.saporiveloce.data.entity.Yemekler
import com.aydinkaya.saporiveloce.viewmodel.FavoriScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.YemekKayitViewModel


@Composable
fun YemekCard(
    navController: NavController,
    yemek: Yemekler,
    yemekKayitViewModel: YemekKayitViewModel,
    favoriScreenViewModel: FavoriScreenViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var isFavori by remember { mutableStateOf(favoriScreenViewModel.isFavori(yemek)) }
    val coroutineScope = rememberCoroutineScope()
    var quantity by remember { mutableStateOf(1) }
    var showAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(isFavori) {
        isFavori = favoriScreenViewModel.isFavori(yemek)
    }

    Card(
        modifier = Modifier
            .width(240.dp)
            .padding(8.dp)
            .clickable {
                Log.d("YemekCard", "Detay sayfasına yönlendiriliyor: ${yemek.yemek_adi}")
                navController.navigate("productDetail/${yemek.yemek_id}/${yemek.yemek_adi}/${yemek.yemek_fiyat}/${yemek.yemek_resim_adi}")

            },
        colors = CardDefaults.cardColors(Color(0xFFFFC107)),
        //colors = CardDefaults.cardColors(Color(0xFFFFF479))
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(2.dp),
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
                IconButton(
                    onClick = {
                        isFavori = !isFavori

                        if (isFavori) {
                            favoriScreenViewModel.favoriEkle(yemek)
                        } else {
                            favoriScreenViewModel.favoriCikar(yemek)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(id = if (isFavori) R.drawable.unfavori else R.drawable.favori),
                        contentDescription = "Favorite",
                        tint = Color.White
                    )
                }

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
                text = "$${yemek.yemek_fiyat}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .shadow(16.dp, shape = RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFFFF6D00), Color.Transparent),
                            radius = 300f
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(8.dp)
            ) {
                Button(
                    onClick = {
                        Log.d(
                            "YemekCard",
                            "Butona tıklandı, yemek sepete eklenecek: ${yemek.yemek_adi}"
                        )
                        yemekKayitViewModel.kaydet(
                            yemek.yemek_adi,
                            yemek.yemek_resim_adi,
                            yemek.yemek_fiyat,
                            quantity
                        )
                        showAnimation = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Add to cart", color = Color.White)
                }


            }


        }
    }
}
