package com.example.bottombardemo.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.bottombardemo.ui.theme.Grey_200
import com.example.bottombardemo.viewmodels.HomeViewModel

@Composable
fun Home(
    viewModel: HomeViewModel,
    clueCount: Int,
    clueWordList: List<String>,
    clueStatusList: List<String>
) {
    val clueListState = rememberLazyListState()
    var typedClueText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Log.d("Home", "clueCount: $clueCount,\nclueWordList: $clueWordList, \nclueStatusList: $clueStatusList \n") // Debug

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(color = Grey_200),
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(state = clueListState) {
            items(clueCount) {
                Text(clueWordList[it] + " " + clueStatusList[it])
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedTextField(
                value = typedClueText,
                onValueChange = { typedClueText = it.uppercase() },
                label = { Text("New Clue") },
                singleLine = true,
                textStyle = TextStyle(fontFamily = FontFamily.Monospace),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
            )
            Button(onClick = {
                keyboardController?.hide()
                viewModel.addClue(typedClueText,"BBBBB")
                typedClueText = ""
            }) {
                Text(text = "Add")

            }
        }


    }
}

//@Composable
//fun ClueItem(s: String) {
//    Text(text = s)
//}
//
//@Composable
//fun RenderClueItem(c: Clue) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Button(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .clip(CircleShape) // Make the button circular
//                .size(56.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Grey_200, contentColor = White)
//        )
//        {
//            Text(text = c.word)
//        }
//
//    }
//}
