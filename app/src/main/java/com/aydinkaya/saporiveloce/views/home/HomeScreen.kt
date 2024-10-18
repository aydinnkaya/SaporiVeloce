package com.aydinkaya.saporiveloce.views.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.R
import com.aydinkaya.saporiveloce.viewmodel.FavoriScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.FoodCardData
import com.aydinkaya.saporiveloce.viewmodel.HomeScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.YemekKayitViewModel
import com.example.graduationproject.views.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    yemekKayitViewModel: YemekKayitViewModel = hiltViewModel(),
    favoriScreenViewModel: FavoriScreenViewModel = hiltViewModel()
) {
    val foodCards by viewModel.foodCards.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val foodItems by viewModel.foodItems.collectAsState()
    val restaurants by viewModel.restaurants.collectAsState()
    val yemeklerListesi by homeViewModel.yemeklerListesi.observeAsState(emptyList())


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            WelcomeAndLocation(navController)
        }

        item {
            SearchBar()
        }

        item {
            LazyRow(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (foodCards.isNotEmpty()) {
                    items(foodCards) { card ->
                        FoodCard(card = card)
                    }
                } else {
                    item {
                        Text("No food cards available")
                    }
                }
            }
        }

        item {
            Text(text = "What's On your Mood", style = MaterialTheme.typography.titleMedium)
            FoodCategoryScreen()
        }

        item {

            YemekListScreen(
                navController = navController,
                yemekList = yemeklerListesi,
                yemekKayitViewModel,
                favoriScreenViewModel
            )

        }


        /*
        item {
            Text(text = "Popular Restaurant", style = MaterialTheme.typography.titleMedium)
        }

        if (restaurants.isNotEmpty()) {
            items(restaurants) { restaurant ->
                RestaurantCard(restaurant = restaurant)
            }
        } else {
            item {
                Text("No restaurants available")
            }
        }

         */
    }
}

