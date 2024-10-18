package com.aydinkaya.saporiveloce.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aydinkaya.saporiveloce.R

@Composable
fun RestaurantCard(restaurant: String) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable { }
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.sandwich),
                contentDescription = restaurant
            )
            Text(text = restaurant, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
