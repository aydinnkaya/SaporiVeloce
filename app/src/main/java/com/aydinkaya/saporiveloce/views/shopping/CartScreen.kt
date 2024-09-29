package com.aydinkaya.saporiveloce.views.shopping

import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aydinkaya.saporiveloce.viewmodel.YemekViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(viewModel: YemekViewModel = hiltViewModel()) {
    val cartItems by viewModel.sepetYemekler.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sepetim") }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            if (cartItems.isNotEmpty()) {
                CartItemList(cartItems = cartItems, onRemoveItem = { item ->
                    viewModel.sepettenYemekSil(item.sepet_yemek_id, "Aydin")
                })
            } else {
                Text("Sepetiniz boş.", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

