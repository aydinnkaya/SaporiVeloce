package com.aydinkaya.saporiveloce.views.shopping

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import com.aydinkaya.saporiveloce.data.entity.SepetYemek

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: YemekViewModel,
    navController: NavController
) {
    // LiveData'yı observeAsState ile dinliyoruz
    val cartItems by viewModel.cartItems.observeAsState(emptyList())
    val toplamFiyat by remember { derivedStateOf { viewModel.toplamFiyat() } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sepetim") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { sepetYemek ->
                    CartItem(sepetYemek = sepetYemek, viewModel = viewModel)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Toplam Fiyat", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "₺$toplamFiyat", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Siparişi Tamamla */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Siparişi Tamamla", color = Color.White)
            }
        }
    }
}

@Composable
fun CartItem(
    sepetYemek: SepetYemek,
    viewModel: YemekViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"),
            contentDescription = sepetYemek.yemek_adi,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.weight(1f).padding(start = 16.dp)
        ) {
            Text(text = sepetYemek.yemek_adi, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "₺${sepetYemek.yemek_fiyat}", fontSize = 14.sp)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                if (sepetYemek.yemek_siparis_adet > 1) {
                    viewModel.updateCartQuantity(sepetYemek, sepetYemek.yemek_siparis_adet - 1)
                } else {
                    viewModel.sepettenYemekSil(sepetYemek)  // Miktar 1 olduğunda, öğeyi sepetten sil
                }
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Azalt")
            }

            Text(text = "${sepetYemek.yemek_siparis_adet}", fontSize = 16.sp, fontWeight = FontWeight.Bold)

            IconButton(onClick = {
                viewModel.updateCartQuantity(sepetYemek, sepetYemek.yemek_siparis_adet + 1)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Arttır")
            }
        }
    }
}




/*
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aydinkaya.saporiveloce.R
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: YemekViewModel = hiltViewModel()
) {
    val cartItems by viewModel.cartItems.observeAsState(emptyList())
    val totalPrice by remember { derivedStateOf { viewModel.toplamFiyat() } }

    Log.d("CartScreen", "Sepet verileri güncelleniyor: ${cartItems.size} öğe mevcut.")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        Log.d("Navigation", "Geri butonuna basıldı.")
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
                    .padding(16.dp)
            ) {

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(cartItems) { cartItem ->
                            Log.d("CartScreen", "Sepet Öğesi: ${cartItem.yemek_adi}, Miktar: ${cartItem.yemek_siparis_adet}")
                            CartItem(
                                imageRes = cartItem.yemek_resim_adi,
                                title = cartItem.yemek_adi,
                                price = cartItem.yemek_fiyat,
                                quantity = cartItem.yemek_siparis_adet,
                                onQuantityChange = { newQuantity ->
                                    viewModel.updateCartQuantity(cartItem, newQuantity)
                                    Log.d("CartScreen", "${cartItem.yemek_adi} miktarı güncellendi: $newQuantity")
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    HorizontalDivider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Total", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text(text = "$${totalPrice}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Log.d("CartScreen", "Toplam fiyat: $totalPrice")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    HorizontalDivider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { Log.d("CartScreen", "Checkout butonuna basıldı.") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Text(text = "Checkout", fontSize = 18.sp)
                    }
                }
            }

    )
}

@Composable
fun CartItem(imageRes: String, title: String, price: Int, quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/$imageRes"),
            contentDescription = title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "$${price * quantity}", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color(0xFFFFA726))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (quantity > 1) onQuantityChange(quantity - 1) }) {
                Icon(
                    painter = painterResource(id = R.drawable.minusred),
                    contentDescription = "Decrease",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(text = quantity.toString(), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

            IconButton(onClick = { onQuantityChange(quantity + 1) }) {
                Icon(Icons.Default.Add, contentDescription = "Increase", tint = Color.Black)
            }
        }
    }
}

 */
