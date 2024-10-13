package com.aydinkaya.saporiveloce.views.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    yemek: Yemekler, // Assuming 'Yemekler' is a data class for the food items
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
                //navController.navigate("detaySayfa/${yemek.yemek_id}/${yemek.yemek_adi}/${yemek.yemek_fiyat}/${yemek.yemek_resim_adi}")
                navController.navigate("productDetail/${yemek.yemek_id}/${yemek.yemek_adi}/${yemek.yemek_fiyat}/${yemek.yemek_resim_adi}")

            },
        colors = CardDefaults.cardColors(Color.White),
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
                        tint = Color(0xFFFF9800)
                    )
                }
                /*
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(8.dp),
                    tint =Color(0xFFFF9800)

                )

                 */
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
                    Log.d("YemekCard", "Butona tıklandı, yemek sepete eklenecek: ${yemek.yemek_adi}")

                        yemekKayitViewModel.kaydet(
                            yemek.yemek_adi,
                            yemek.yemek_resim_adi,
                            yemek.yemek_fiyat,
                            quantity
                        )
                        showAnimation = true



                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sepete Ekle", color = Color.Black)
            }
        }
    }
}



/*

@Composable
fun YemekCard(
    yemek: Yemek,
    navController: NavController,
    viewModel: YemekViewModel = hiltViewModel()

) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .width(240.dp)
            .padding(8.dp)
            .clickable {
                Log.d("YemekCard", "Detay sayfasına yönlendiriliyor: ${yemek.yemek_adi}")
                navController.navigate("productDetail/${yemek.yemek_id}/${yemek.yemek_adi}/${yemek.yemek_fiyat}/${yemek.yemek_resim_adi}/Açıklama")
            },
        colors = CardDefaults.cardColors(Color.White),
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
                        .padding(8.dp),
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
                    Log.d("YemekCard", "Butona tıklandı, yemek sepete eklenecek: ${yemek.yemek_adi}")

                    val sepetYemek = SepetYemek(
                        sepet_yemek_id = 0,
                        yemek_adi = yemek.yemek_adi,
                        yemek_resim_adi = yemek.yemek_resim_adi,
                        yemek_fiyat = yemek.yemek_fiyat,
                        yemek_siparis_adet = 1,
                        kullanici_adi = "realicon"
                    )

                    viewModel.sepeteYemekEkle(sepetYemek)

                    Log.d("YemekCard", "${yemek.yemek_adi} sepete ekleniyor...")
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "${yemek.yemek_adi} sepete eklendi",
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
                Text(text = "Sepete Ekle", color = Color.Black)
            }
        }
    }
}



 */