package com.aydinkaya.saporiveloce.views.shopping

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import coil.compose.rememberAsyncImagePainter
import com.aydinkaya.saporiveloce.data.entity.SepetYemek


@Composable
fun CartItemCard(sepetYemek: SepetYemek, onRemoveItem: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(sepetYemek.yemek_adi, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Fiyat: ₺${sepetYemek.yemek_fiyat}", style = MaterialTheme.typography.bodyMedium)
                Text("Adet: ${sepetYemek.yemek_siparis_adet}", style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(onClick = onRemoveItem) {
                Icon(Icons.Default.Delete, contentDescription = "Remove item")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text("₺${sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet}")
        }
    }
}

