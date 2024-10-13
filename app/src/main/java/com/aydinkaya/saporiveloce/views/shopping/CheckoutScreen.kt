package com.aydinkaya.saporiveloce.views.shopping

/*
@Composable
fun CartItemCard(sepetYemek: SepetYemek, onRemoveItem: () -> Unit, viewModel: YemekViewModel/*
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import androidx.compose.material3.*
import coil.compose.rememberAsyncImagePainter
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CheckoutScreen(viewModel: YemekViewModel = hiltViewModel()) {
    val cartItems by viewModel.cartItems.observeAsState(emptyList())
    val toplamFiyat by viewModel.toplamFiyat.observeAsState(0.0)

    LaunchedEffect(Unit) {
        viewModel.tumSepetYemekleriGetir(kullaniciAdi = "realicon")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Geri git */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
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
                    .padding(padding)
                    .padding(16.dp)
                    .background(Color(0xFFF6F6F6)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (cartItems.isEmpty()) {
                    Text(
                        text = "Sepetiniz boş",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                } else {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = "Deliver To: Home", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(text = "13A Havinir Street, New York", fontSize = 14.sp, color = Color.Gray)
                            }
                            TextButton(onClick = {  }) {
                                Text(text = "Change", color = Color(0xFF1E88E5))
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        cartItems.forEach { sepetYemek ->
                            CartItemCard(
                                sepetYemek = sepetYemek,
                                onRemoveItem = {
                                    viewModel.sepettenYemekSil(sepetYemek)
                                },
                                viewModel = viewModel // viewModel'i buradan gönderiyoruz
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        PriceSummary(itemTotal = toplamFiyat, deliveryFee = 0.00)

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50)
                            )
                        ) {
                            Text(
                                text = "Confirm order",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    )
}


 */
) {
    var quantity by remember { mutableStateOf(sepetYemek.yemek_siparis_adet) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"),
                contentDescription = sepetYemek.yemek_adi,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = sepetYemek.yemek_adi, fontWeight = FontWeight.Bold)
                Text(text = "${sepetYemek.yemek_fiyat}₺ x $quantity")
            }

            IconButton(onClick = {
                if (quantity > 1) {
                    quantity--
                    viewModel.updateCartQuantity(sepetYemek, quantity)
                }
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Decrease")
            }

            Text(text = "$quantity", fontWeight = FontWeight.Bold)

            IconButton(onClick = {
                quantity++
                viewModel.updateCartQuantity(sepetYemek, quantity)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Increase")
            }

            IconButton(onClick = onRemoveItem) {
                Icon(Icons.Default.Delete, contentDescription = "Remove")
            }
        }
    }
}


@Composable
fun PriceSummary(itemTotal: Double, deliveryFee: Double) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Item Total")
            Text(text = "$${String.format("%.2f", itemTotal)}")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Delivery Fee")
            Text(text = if (deliveryFee == 0.0) "Free" else "$${String.format("%.2f", deliveryFee)}")
        }
        Divider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total", fontWeight = FontWeight.Bold)
            Text(text = "$${String.format("%.2f", itemTotal + deliveryFee)}", fontWeight = FontWeight.Bold)
        }
    }
}






/*
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CheckoutScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
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
                    .padding(padding)
                    .padding(16.dp)
                    .background(Color(0xFFF6F6F6))
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Deliver To: Home", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "13A Havinir Street, New York", fontSize = 14.sp, color = Color.Gray)
                    }
                    TextButton(onClick = {  }) {
                        Text(text = "Change", color = Color(0xFF1E88E5))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberImagePainter(data = "https://example.com/burger_image_url"),
                        contentDescription = "Crispy Burger",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp)), // Rounded corners
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Crispy Burger", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "$30.00", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Decrease quantity", tint = Color.Black)
                            }
                            Text(text = "1", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            IconButton(
                                onClick = {  },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Increase quantity", tint = Color.Black)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Payment Method", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    TextButton(onClick = {  }) {
                        Text(text = "Add New", color = Color(0xFF1E88E5))
                    }
                }

                PaymentOptionItem(
                    methodName = "PayPal",
                    description = "Faster & safer way to send money",
                    selected = false
                )

                PaymentOptionItem(
                    methodName = "Credit Card",
                    description = "Pay with MasterCard, Visa or Visa",
                    selected = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Add Promo Code", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Add promo code", tint = Color.Black)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                PriceSummary(itemTotal = 50.00, deliveryFee = 0.00)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Confirm order action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Text(text = "Confirm order", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

@Composable
fun PaymentOptionItem(methodName: String, description: String, selected: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Star, contentDescription = methodName, tint = Color.Gray)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = methodName, fontWeight = FontWeight.Bold)
            Text(text = description, color = Color.Gray, fontSize = 12.sp)
        }
        if (selected) {
            Icon(Icons.Default.Check, contentDescription = "Selected", tint = Color(0xFF4CAF50))
        }
    }
}

@Composable
fun PriceSummary(itemTotal: Double, deliveryFee: Double) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Item Total")
            Text(text = "$${String.format("%.2f", itemTotal)}")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Delivery Fee")
            Text(text = if (deliveryFee == 0.0) "Free" else "$${String.format("%.2f", deliveryFee)}")
        }
        Divider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total", fontWeight = FontWeight.Bold)
            Text(text = "$${String.format("%.2f", itemTotal + deliveryFee)}", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen()
}


 */
 */