package com.aydinkaya.saporiveloce.views.productdetailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.aydinkaya.saporiveloce.R
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    yemekId: Int,
    yemekAdi: String,
    yemekFiyat: String,
    yemekResimAdi: String,
    yemekAciklama: String,
    onBackPress: () -> Unit,
    viewModel: YemekViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
        //  .background(Color.Black),
        , contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 32.dp,
                        start = 12.dp,
                        end = 16.dp,
                        bottom = 12.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBackPress() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Back",
                        tint = Color(0xFFFF6D00)
                    )
                }

                IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(50))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cartimage),
                        contentDescription = "Shopping Cart",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .height(500.dp)
                    .size(500.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFFFF6D00), Color.Unspecified),
                            radius = 500f
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/$yemekResimAdi"),
                    contentDescription = yemekAdi,
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }


            Spacer(modifier = Modifier.height(4.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "5.0",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = Color(0xFFFF6D00)
                        )

                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "275", color = Color.White)
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.Start) {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = yemekAdi,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = yemekAciklama,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 14.sp
                            )
                        )
                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { /* Miktar azalt */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.minusred),
                            contentDescription = "Decrease Quantity",
                            tint = Color(0xFFFF6D00),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(text = "1", color = Color.White)
                    IconButton(onClick = { /* Miktar artır */ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase Quantity",
                            tint = Color(0xFFFF6D00)
                        )
                    }
                }

                Text(
                    text = "₺$yemekFiyat",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }

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
                    onClick = {  },
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


/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
navController: NavHostController,
yemekId: Int,
yemekAdi: String,
yemekFiyat: String,
yemekResimAdi: String,
onBackPress: () -> Unit,
viewModel: YemekViewModel = hiltViewModel()
) {
Scaffold(
    topBar = {
        TopAppBar(
            title = { Text("Product Detail") },
            navigationIcon = {
                IconButton(onClick = { onBackPress() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }
) { contentPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/$yemekResimAdi"),
            contentDescription = yemekAdi,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = yemekAdi,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "$yemekFiyat₺",
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color(0xFFFF9800),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val sepetYemek = SepetYemek(
                    sepet_yemek_id = 0,
                    yemek_adi = yemekAdi,
                    yemek_resim_adi = yemekResimAdi,
                    yemek_fiyat = yemekFiyat.toInt(),
                    yemek_siparis_adet = 1,
                    kullanici_adi = "kasim_adalan"
                )
                viewModel.sepeteYemekEkle(sepetYemek)

                navController.navigate("cart")
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
