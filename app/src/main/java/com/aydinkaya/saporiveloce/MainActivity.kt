package com.aydinkaya.saporiveloce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aydinkaya.saporiveloce.ui.theme.SaporiVeloceTheme
import com.aydinkaya.saporiveloce.views.home.NavigationStack
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaporiVeloceTheme {
               NavigationStack()
            }
        }
    }
}

