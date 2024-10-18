package com.aydinkaya.saporiveloce.views.shopping

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.R
import com.aydinkaya.saporiveloce.viewmodel.SepetViewModel
import com.bumptech.glide.Glide
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    sepetViewModel: SepetViewModel
) {
    val sepettekiYemekler by sepetViewModel.sepettekiYemeklerListesi.observeAsState(emptyList())
    var showAnimation by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        sepetViewModel.sepettekiYemekleriGetir("aydinkaya")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "My Cart",
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF9800),
                fontSize = 20.sp,
            )
            IconButton(onClick = { /* Handle notifications */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        if (sepettekiYemekler.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Your Cart is Empty", color = Color.Black, fontSize = 18.sp)
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
                    .height(400.dp),
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
                        colors = CardDefaults.cardColors(containerColor =  Color(0xFFFFA500))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Food Image
                            Image(
                                painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = yemek.yemek_adi,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "$${yemek.yemek_fiyat}",
                                    fontSize = 16.sp,
                                    color = Color.White
                                )

                            }
                            Spacer(modifier = Modifier.width(16.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = { /* Decrease quantity */ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.minusred),
                                        contentDescription = "Remove",
                                        tint = Color.Unspecified ,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                Text(
                                    text = "$quantity",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add"
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                IconButton(onClick = {
                                    sepetViewModel.sil(
                                        yemek.sepet_yemek_id.toInt(),
                                        yemek.kullanici_adi
                                    )
                                }) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.Red
                                    )
                                }

                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Total and Checkout Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Item Total
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Item Total", fontWeight = FontWeight.Bold)
                    Text("$$toplamFiyat")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Delivery Fee
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Delivery Fee", fontWeight = FontWeight.Bold)
                    Text(if (getirmeUcreti == 0.00) "Free" else "$$getirmeUcreti")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Total Amount
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("$$toplamTutar", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.maps),
                        contentDescription = "Map Icon",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .align(Alignment.CenterVertically)
                    )

                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Deliver To: Home",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "13A Havinir Street, New York",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    TextButton(onClick = { /* Handle change address */ }) {
                        Text(text = "Change", color = Color(0xFF1E88E5))
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

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
                            showAnimation = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Checkout", color = Color.White)
                    }


                    if (showAnimation) {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFFFF6D00),
                                            Color.Transparent
                                        ),
                                        radius = 250f,
                                        center = Offset(0.5f, 0.5f)
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                val composition by rememberLottieComposition(LottieCompositionSpec.Asset("siparisinizalindi.json"))
                                LottieAnimation(
                                    composition = composition,
                                    iterations = 1,
                                    modifier = Modifier.size(50.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Order Received!",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            LaunchedEffect(showAnimation) {
                                delay(3000)
                                showAnimation = false
                                navController.navigate("orderDetailsScreen/$toplamTutar")
                            }
                        }
                    }
                }
            }
        }
    }

}



