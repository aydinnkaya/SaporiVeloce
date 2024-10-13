package com.aydinkaya.saporiveloce.views.productdetailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aydinkaya.saporiveloce.R
import com.aydinkaya.saporiveloce.viewmodel.YemekKayitViewModel
import com.example.graduationproject.views.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    yemekId: Int,
    yemekAdi: String,
    yemekFiyati: Int,
    yemekResimAdi: String,
    yemekKayitViewModel: YemekKayitViewModel,
    onBackPress: () -> Unit,
) {
    val yemekAciklama = homeViewModel.getYemekAciklama(yemekId)
    var showAnimation by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(1) }
    var totalPrice by remember { mutableStateOf(yemekFiyati) }
    var cartItemCount by remember { mutableStateOf(0) }

    LaunchedEffect(quantity) {
        totalPrice = quantity * yemekFiyati
    }

    fun addToCart() {
        cartItemCount += quantity
        yemekKayitViewModel.kaydet(yemekAdi, yemekResimAdi, yemekFiyati, quantity)
        showAnimation = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBackPress() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            navController.navigate("sepetsayfa")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cartimage),
                        contentDescription = "Shopping Cart",
                        modifier = Modifier.size(40.dp)
                    )
                    if (cartItemCount > 0) {
                        Badge(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset((-1).dp, 4.dp),
                            containerColor = Color.DarkGray
                        ) {
                            Text(
                                text = cartItemCount.toString(),
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(350.dp)
                    .graphicsLayer {
                        shadowElevation = 32.dp.toPx()
                        shape = RoundedCornerShape(50)
                        clip = true
                    }
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFF3D00),
                                Color(0xFF000000)
                            ),
                            radius = 500f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/$yemekResimAdi"),
                    contentDescription = yemekAdi,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(50))
                        .graphicsLayer {
                            shadowElevation = 20.dp.toPx()
                            shape = RoundedCornerShape(50)
                            translationY = 10f
                            rotationX = 5f
                        },
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = yemekAdi,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = yemekAciklama,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        if (quantity > 1) quantity--
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.minusred),
                            contentDescription = "Decrease Quantity",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(text = "$quantity", color = Color.Black)
                    IconButton(onClick = { quantity++ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase Quantity",
                            tint = Color.Black
                        )
                    }
                }

                Text(
                    text = "₺$totalPrice",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
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
                        addToCart()
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


