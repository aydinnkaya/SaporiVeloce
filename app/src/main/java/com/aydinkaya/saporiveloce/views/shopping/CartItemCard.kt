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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.aydinkaya.saporiveloce.data.entity.SepetYemek


@Composable
fun CartItemCard(sepetYemek: SepetYemek, onRemoveItem: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"),
                contentDescription = sepetYemek.yemek_adi,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = sepetYemek.yemek_adi, fontWeight = FontWeight.Bold)
                Text(text = "${sepetYemek.yemek_fiyat}₺ x ${sepetYemek.yemek_siparis_adet}")
            }

            IconButton(onClick = onRemoveItem) {
                Icon(Icons.Default.Delete, contentDescription = "Remove")
            }
        }
    }
}