package com.aydinkaya.saporiveloce.views.bottomnavigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*


@Composable
fun CustomBottomNavItem(
    navigateScreens: NavigateScreens,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconColor = if (isSelected) Color.Yellow else Color.White

    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(if (isSelected) Color.Black else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = navigateScreens.icon,
            contentDescription = navigateScreens.title,
            tint = iconColor,
            modifier = Modifier.size(if (isSelected) 32.dp else 24.dp)
        )
    }
}

