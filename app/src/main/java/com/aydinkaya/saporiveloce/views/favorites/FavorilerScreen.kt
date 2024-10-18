package com.aydinkaya.saporiveloce.views.favorites


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.R
import com.aydinkaya.saporiveloce.viewmodel.FavoriScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.YemekKayitViewModel
import com.aydinkaya.saporiveloce.views.home.YemekCard
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.font.FontWeight


@Composable
fun FavorilerScreen(
    navController: NavController,
    favoriScreenViewModel: FavoriScreenViewModel,
    yemekKayitViewModel: YemekKayitViewModel,
    onBackPress: () -> Unit,
) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "My Favorites",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF9800),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            val favoriteMeals = favoriScreenViewModel.favoriYemekler

            if (favoriteMeals.isEmpty()) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Image(
                        modifier = Modifier.size(200.dp, 200.dp),
                        painter = painterResource(id = R.drawable.not_found),
                        contentDescription = "No Favorites"
                    )
                    Text(
                        text = "You have no favorite items.",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(8.dp),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(favoriteMeals) { meal ->
                        YemekCard(
                            yemek = meal,
                            navController = navController,
                            yemekKayitViewModel = yemekKayitViewModel,
                            favoriScreenViewModel = favoriScreenViewModel
                        )
                    }
                }

        }
    }
}



/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavorilerSayfa(navController: NavController, favoriViewModel: FavoriViewModel, yemekKayitViewModel: YemekKayitViewModel) {

    var showAnimation by remember { mutableStateOf(false) }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Appbarcolor,
        darkIcons = false
    )

    Scaffold(topBar = {
        Surface(
            shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
            color = Appbarcolor
        ) {
            CenterAlignedTopAppBar(
                title = { Text(text = "Favorilerim") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("bottomBarSayfa") {
                            popUpTo("bottomBarSayfa") { inclusive = true }
                            launchSingleTop = true }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Appbarcolor, titleContentColor = Color.White)
            )
        }
    }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            val favoriYemekler = favoriViewModel.favoriYemekler

            if (favoriYemekler.isEmpty()) {

                Image(modifier = Modifier.size(150.dp,200.dp), painter = painterResource(id = R.drawable.bulunmaya), contentDescription = "Sepet Boş")
                Text("Favorilerinizde Ürün Bulunmuyor", color = Color.Black, fontSize = 18.sp)
            } else {
                LazyColumn {
                    items(favoriYemekler) { yemek ->
                        val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
                        var quantity by remember { mutableStateOf(1) }

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                                .background(Color.White)
                        ) {
                            Card(
                                modifier = Modifier.fillMaxSize(),
                                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                shape = MaterialTheme.shapes.medium.copy(all = CornerSize(8.dp)),
                                border = BorderStroke(1.dp, Color.White)
                            ) {
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("detaySayfa/${yemek.yemek_id}/${yemek.yemek_adi}/${yemek.yemek_fiyat}/${yemek.yemek_resim_adi}")
                                    }) {
                                    AndroidView(
                                        factory = { context ->
                                            ImageView(context).apply {
                                                Glide.with(context)
                                                    .load(resimUrl)
                                                    .into(this)
                                            }
                                        },
                                        modifier = Modifier
                                            .size(80.dp, 100.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )

                                    Text(
                                        text = yemek.yemek_adi,
                                        fontSize = 24.sp,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(top = 8.dp)
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "${yemek.yemek_fiyat} ₺",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f)
                                        )

                                        IconButton(
                                            onClick = {
                                                yemekKayitViewModel.kaydet(
                                                    yemek.yemek_adi,
                                                    yemek.yemek_resim_adi,
                                                    yemek.yemek_fiyat,
                                                    quantity
                                                )
                                                showAnimation = true
                                            },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.plus),
                                                contentDescription = "plus",
                                            )
                                        }
                                    }
                                }
                            }

                            var isFavori by remember { mutableStateOf(favoriViewModel.isFavori(yemek)) }
                            IconButton(
                                onClick = {

                                    isFavori = !isFavori

                                    if (isFavori) {
                                        favoriViewModel.favoriEkle(yemek)
                                    } else {
                                        favoriViewModel.favoriCikar(yemek)
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {

                                Icon(
                                    painter = painterResource(id = if (isFavori) R.drawable.unfavori else R.drawable.favori),
                                    contentDescription = "Favorite",
                                    tint = Appbarcolor
                                )
                            }
                        }
                    }
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
                val composition by rememberLottieComposition(LottieCompositionSpec.Asset("adcartt.json"))
                LottieAnimation(
                    composition = composition,
                    iterations = 1,
                    modifier = Modifier.size(150.dp)
                )


                LaunchedEffect(showAnimation) {
                    delay(2000)
                    showAnimation = false
                }
            }
        }
    }
}


 */
