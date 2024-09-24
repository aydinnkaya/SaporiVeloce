package com.aydinkaya.saporiveloce.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aydinkaya.saporiveloce.R


@Composable
fun FoodCategoryScreen() {
    val foodCategories = listOf(
        Pair("Pizza", R.drawable.pizza2),
        Pair("Asian", R.drawable.noodle),
        Pair("Burger", R.drawable.burger1),
        Pair("Dessert", R.drawable.baklava),
        Pair("Sandwich", R.drawable.sandwich),
        Pair("Fast Food", R.drawable.fastfood),
        Pair("Fish", R.drawable.fish)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        foodCategories.forEach { category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(id = category.second),
                    contentDescription = category.first,
                    modifier = Modifier.size(64.dp)
                )
                Text(text = category.first, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFoodCategoryScreen() {
    FoodCategoryScreen()
}