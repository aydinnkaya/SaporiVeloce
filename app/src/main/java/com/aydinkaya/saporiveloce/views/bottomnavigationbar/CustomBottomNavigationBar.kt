package com.aydinkaya.saporiveloce.views.bottomnavigationbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.draw.shadow
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CustomBottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Cart,
        Screen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                clip = false
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFA726), // Light Orange
                        Color(0xFFFF5722)  // Dark Orange
                    )
                )
            )
    ) {
        // Wave effect
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(width * 0.25f, 0f)
                cubicTo(width * 0.35f, 0f, width * 0.35f, height * 0.6f, width * 0.5f, height * 0.6f)
                cubicTo(width * 0.65f, height * 0.6f, width * 0.65f, 0f, width * 0.75f, 0f)
                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }
            drawPath(path, color = Color.Black.copy(alpha = 0.1f))
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { screen ->
                val isSelected = currentRoute == screen.route
                CustomBottomNavItem(
                    screen = screen,
                    isSelected = isSelected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}