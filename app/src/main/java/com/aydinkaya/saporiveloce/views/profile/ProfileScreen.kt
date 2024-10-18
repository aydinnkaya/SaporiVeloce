package com.aydinkaya.saporiveloce.views.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.R
import com.example.graduationproject.data.entity.HesabimKategoriler

@Composable
fun ProfileScreen(navController: NavController) {
    val kategoriler = remember { mutableStateListOf<HesabimKategoriler>() }

    LaunchedEffect(key1 = true) {
        val f1 = HesabimKategoriler(1, "adres", "My Addresses")
        val f2 = HesabimKategoriler(2, "siparislerim", "My Orders")
        val f3 = HesabimKategoriler(3, "favori_resim", "My Favorites")
        val f4 = HesabimKategoriler(4, "kartlarim", "My Saved Cards")
        val f5 = HesabimKategoriler(5, "hesapayarlari", "Account Settings")
        val f6 = HesabimKategoriler(6, "language", "Language & App Settings")
        val f7 = HesabimKategoriler(7, "sorular", "Frequently Asked Questions")

        kategoriler.add(f1)
        kategoriler.add(f2)
        kategoriler.add(f3)
        kategoriler.add(f4)
        kategoriler.add(f5)
        kategoriler.add(f6)
        kategoriler.add(f7)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(140.dp))
                Text(
                    text = "Hello AYDIN!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color =Color(0xFFFFC107)

                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            columns = GridCells.Fixed(1)
        ) {
            items(
                count = kategoriler.count(),
                itemContent = { index ->
                    val title = kategoriler[index]
                    Card(modifier = Modifier.padding(all = 5.dp),
                        colors = CardDefaults.cardColors(Color(0xFFFFC107))) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color(0xFFFFC107),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    when (title.id) {
                                        1 -> navController.navigate("adres")
                                        2 -> navController.navigate("orderDetailsScreen/$500.00")
                                        3 -> navController.navigate("favorisayfa")
                                        4 -> navController.navigate("kartlarim")
                                        5 -> navController.navigate("hesapayarlari")
                                        6 -> navController.navigate("language")
                                        7 -> navController.navigate("sorular")
                                    }
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                val resimId = LocalContext.current.resources.getIdentifier(
                                    title.resim,
                                    "drawable",
                                    LocalContext.current.packageName
                                )
                                Image(
                                    painter = painterResource(id = resimId),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp, 24.dp)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = title.title,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                Image(
                                    painter = painterResource(id = R.drawable.rightarrow),
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
