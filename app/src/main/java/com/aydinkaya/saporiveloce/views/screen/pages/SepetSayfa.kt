package com.aydinkaya.saporiveloce.views.screen.pages

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aydinkaya.saporiveloce.R

import com.aydinkaya.saporiveloce.ui.theme.Appbarcolor
import com.aydinkaya.saporiveloce.ui.theme.kartarkaplan
import com.aydinkaya.saporiveloce.viewmodel.SepetViewModel
import com.bumptech.glide.Glide
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SepetSayfa(
    navController: NavController,
    sepetViewModel: SepetViewModel
) {
    val sepettekiYemekler by sepetViewModel.sepettekiYemeklerListesi.observeAsState(emptyList())
    var showAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        sepetViewModel.sepettekiYemekleriGetir("aliardal")
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Appbarcolor,
        darkIcons = false
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Sepetim(${sepettekiYemekler.size})") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                            launchSingleTop = true
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Appbarcolor,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (sepettekiYemekler.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(modifier = Modifier.size(150.dp,200.dp), painter = painterResource(id = R.drawable.bulunmaya), contentDescription = "Sepet Boş")
                        Text("Sepetinizde Ürün Bulunmuyor", color = Color.Black, fontSize = 18.sp)
                    }
                }

            } else {
                val toplamFiyat = sepettekiYemekler.sumOf {
                    it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt()
                }

                val getirmeUcreti = if (toplamFiyat >= 500) 0.00 else 24.99
                val toplamTutar = toplamFiyat + getirmeUcreti

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(sepettekiYemekler, key = { yemek -> yemek.sepet_yemek_id }) { yemek ->
                        val price = yemek.yemek_fiyat.toInt()
                        val quantity = yemek.yemek_siparis_adet.toInt()
                        val totalPrice = price * quantity

                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                AndroidView(
                                    factory = { context ->
                                        ImageView(context).apply {
                                            Glide.with(context)
                                                .load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}")
                                                .into(this)
                                        }
                                    },
                                    modifier = Modifier
                                        .size(80.dp, 100.dp)
                                        .align(Alignment.CenterVertically)
                                )

                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 8.dp, end = 8.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = yemek.yemek_adi,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(bottom = 4.dp),
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "Fiyat: ₺${yemek.yemek_fiyat}",
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "Adet: ${yemek.yemek_siparis_adet}",
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .align(Alignment.CenterVertically),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    IconButton(onClick = {
                                        sepetViewModel.sil(yemek.sepet_yemek_id.toInt(), yemek.kullanici_adi)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete),
                                            contentDescription = "Sil",
                                            tint = Appbarcolor,
                                            modifier = Modifier.padding(bottom = 16.dp)
                                        )
                                    }

                                    Text(
                                        text = "Toplam: ₺${totalPrice}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        modifier = Modifier.padding(end = 16.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Sipariş Özeti",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.Start)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(kartarkaplan)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Sipariş Toplamı: ",
                                fontSize = 14.sp,
                                color = Color.Black
                            )

                            Text(
                                text = "₺$toplamFiyat",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Teslimat Ücreti: ",
                                fontSize = 14.sp,
                                color = Color.Black
                            )

                            Text(
                                text = "₺$getirmeUcreti",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Toplam Tutar: ",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Text(
                                text = "₺$toplamTutar",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }

                Text(
                    text = "Adres / Ödeme Yöntemi",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.Start)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(kartarkaplan)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth().padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Teslimat Adresi:",
                                fontSize = 14.sp,
                                color = Color.Black
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.locationsepet),
                                    contentDescription = "Konum İkonu",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "İstanbul/Ümraniye",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }

                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Ödeme Yöntemi: ",
                                fontSize = 14.sp,
                                color = Color.Black
                            )

                            Row(verticalAlignment = Alignment.CenterVertically){
                                Image(
                                    painter = painterResource(id = R.drawable.turklirasi),
                                    contentDescription = "Para",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = "Kapıda Ödeme",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }


                        }

                    }

                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        showAnimation = true

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Appbarcolor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(text = "Sepeti Onayla", fontSize = 18.sp)
                }
            }
        }

        if (showAnimation) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000), RoundedCornerShape(0.dp)),
                contentAlignment = Alignment.Center
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.Asset("siparisinizalindi.json"))
                LottieAnimation(
                    composition = composition,
                    iterations = 1,
                    modifier = Modifier.size(150.dp)
                )


                LaunchedEffect(showAnimation) {
                    delay(3000)
                    showAnimation = false
                    navController.navigate("siparislerimsayfa") {

                    }
                }
            }
        }
    }
}
