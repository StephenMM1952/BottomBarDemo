package com.example.bottombardemo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bottombardemo.ui.theme.Black
import com.example.bottombardemo.ui.theme.Blue_Grey_400
import com.example.bottombardemo.ui.theme.Grey_900
import com.example.bottombardemo.ui.theme.Red_50
import com.example.bottombardemo.ui.theme.Red_600
import com.example.bottombardemo.ui.theme.Red_700

@Composable
fun Home(
    clueCount: Int,
    clueList: List<String>,
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "home",
            tint = Red_700 ,
            modifier = Modifier.size(150.dp)
                .align(Alignment.Center)
        )
    }
}