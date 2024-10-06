package com.aydinkaya.saporiveloce.views.home

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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aydinkaya.saporiveloce.R
import com.aydinkaya.saporiveloce.data.entity.Yemek
import com.aydinkaya.saporiveloce.viewmodel.FoodCardData
import com.aydinkaya.saporiveloce.viewmodel.HomeScreenViewModel
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel(), yemekViewModel: YemekViewModel = hiltViewModel()   ) {
    val foodCards by viewModel.foodCards.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val foodItems by viewModel.foodItems.collectAsState()
    val restaurants by viewModel.restaurants.collectAsState()
    val yemekList by yemekViewModel.yemekListesi.observeAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            WelcomeAndLocation()
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
            YemekListScreen(navController = navController, yemekList = yemekList, viewModel = yemekViewModel)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeAndLocation() {
    val locations = listOf("Cairo, Egypt", "New York, USA", "London, UK", "Tokyo, Japan")
    var selectedLocation by remember { mutableStateOf(locations[0]) }
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Welcome, User",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Green
            )
            Spacer(modifier = Modifier.height(4.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier.focusRequester(focusRequester) // focusRequester burada ekleniyor
            ) {
                Row(
                    modifier = Modifier
                        .clickable {
                            expanded = true
                            focusRequester.requestFocus() // Odak isteği burada
                        }
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Dropdown Arrow")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = selectedLocation)
                }

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location) },
                            onClick = {
                                selectedLocation = location
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notification Icon",
                modifier = Modifier.size(24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.aydin_kaya_photo),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )
        }
    }
}


@Composable
fun FoodCard(card: FoodCardData) {
    val cardBackgroundColor = Color(0xFFE7A33E)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(cardBackgroundColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = card.title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = card.description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = card.discount, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
            }

            Image(
                painter = painterResource(id = card.imageRes),
                contentDescription = card.title,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun RestaurantCard(restaurant: String) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable { }
    ) {
        Column {
            Image(painter = painterResource(id = R.drawable.sandwich), contentDescription = restaurant)
            Text(text = restaurant, style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    val searchText = remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Microphone Icon",
                tint = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFF2F4F5),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp)
    )
}
