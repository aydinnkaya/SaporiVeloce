package com.aydinkaya.saporiveloce.views.shopping

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.data.entity.Yemek

@Composable
fun CartItemList(cartItems: List<SepetYemek>, onRemoveItem: (SepetYemek) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(cartItems) { item ->
            CartItemCard(sepetYemek = item, onRemoveItem = { onRemoveItem(item) })
        }
    }
}
