package com.aydinkaya.saporiveloce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydinkaya.saporiveloce.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class FoodItem(val name: String, val price: String, val imageRes: Int, val rating: String, val reviews: String)

data class FoodCardData(
    val title: String,
    val description: String,
    val discount: String,
    val imageRes: Int
)
@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _categories = MutableStateFlow(
        listOf("Pizza", "Shakes", "Noodles", "Ice-cream")
    )
    val categories: StateFlow<List<String>> = _categories

    private val _foodItems = MutableStateFlow(
        listOf(
            FoodItem("Wild Potato Burger", "$2.23", R.drawable.burger1, "4.9", "205 reviews"),
            FoodItem("Aloo Paratha", "$2.23", R.drawable.noodle, "4.9", "205 reviews")
        )
    )
    val foodItems: StateFlow<List<FoodItem>> = _foodItems

    private val _restaurants = MutableStateFlow(
        listOf("Subway", "Dominos")
    )
    val restaurants: StateFlow<List<String>> = _restaurants



    private val _foodCards = MutableStateFlow(
        listOf(
            FoodCardData("Burger", "Special offers just for you", "Up to 60%", R.drawable.burger2),
            FoodCardData("Pizza", "Enjoy delicious pizza", "Up to 40%", R.drawable.pizza),
            FoodCardData("Lahmacun", "Authentic flavors", "Up to 30%", R.drawable.sandwich),
            FoodCardData("Dessert", "Satisfy your sweet tooth", "Up to 50%", R.drawable.baklava)
        )
    )
    val foodCards: StateFlow<List<FoodCardData>> = _foodCards



}
