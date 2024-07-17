package com.example.bottombardemo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.bottombardemo.ui.theme.Black
import com.example.bottombardemo.ui.theme.Blue_Grey_400
import com.example.bottombardemo.ui.theme.Grey_900
import com.example.bottombardemo.ui.theme.Red_50
import com.example.bottombardemo.ui.theme.Red_600
import com.example.bottombardemo.ui.theme.Red_700
import com.example.bottombardemo.viewmodels.HomeViewModel

@Composable
fun Home(
    viewModel: HomeViewModel,
    clueCount: Int,
    clueList: List<String>
) {
    val clueListState = rememberLazyListState()
    var clueText by remember {mutableStateOf("")}
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.fillMaxHeight().padding(16.dp),
        verticalArrangement = Arrangement.Top) {
        LazyColumn(state = clueListState) {
            items(clueCount) {
                ClueItem(clueList[it])
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            
            OutlinedTextField(value = clueText,
                onValueChange = { clueText = it },
                label = { Text("New Clue") }
            )
            Button(onClick = {
                keyboardController?.hide()
                viewModel.addClue(clueText)
                clueText = ""
            }) {
                Text(text = "Add",)

            }
        }




    }
}

@Composable
fun ClueItem(s: String) {
    Text(text = s)
}

// Below is the old home screen content
@Composable
private fun OldHome() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "home",
            tint = Red_700,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}
