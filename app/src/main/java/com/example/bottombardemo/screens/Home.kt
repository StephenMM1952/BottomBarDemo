package com.example.bottombardemo.screens

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottombardemo.ui.theme.Black
import com.example.bottombardemo.ui.theme.Blue_200
import com.example.bottombardemo.ui.theme.Blue_300
import com.example.bottombardemo.ui.theme.Blue_600
import com.example.bottombardemo.ui.theme.Green_900
import com.example.bottombardemo.ui.theme.Grey_200
import com.example.bottombardemo.ui.theme.Grey_400
import com.example.bottombardemo.ui.theme.Grey_600
import com.example.bottombardemo.ui.theme.Grey_700
import com.example.bottombardemo.ui.theme.Grey_800
import com.example.bottombardemo.ui.theme.Grey_900
import com.example.bottombardemo.ui.theme.Indigo_50
import com.example.bottombardemo.ui.theme.Indigo_900
import com.example.bottombardemo.ui.theme.Red_400
import com.example.bottombardemo.ui.theme.Red_500
import com.example.bottombardemo.ui.theme.Red_A700
import com.example.bottombardemo.ui.theme.White
import com.example.bottombardemo.ui.theme.Yellow_900
import com.example.bottombardemo.viewmodels.HomeViewModel
import kotlinx.coroutines.coroutineScope

val clueColorMap = mapOf("B" to Grey_700, "G" to Green_900, "Y" to Yellow_900)

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Home(viewModel: HomeViewModel = viewModel()) {
    var clueCount = viewModel.clueCount
    var clueWordList = viewModel.clueWordList
    var clueStatusList = viewModel.clueStatusList

    val clueListState = rememberLazyListState()
    var typedClueText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val buttonContainerSize = 56.dp
    val iconContainerSize = 32.dp

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(0.dp)
            .background(color = Blue_300),
        verticalArrangement = Arrangement.Top
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxHeight(.8f),
//                .background(color = Grey_200),
            state = clueListState,
            verticalArrangement = Arrangement.Bottom
        ) {

            items(clueCount) {

                var clueWord = clueWordList[it]
                var clueStatus = clueStatusList[it]

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(iconContainerSize)
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete word from database",
                            modifier = Modifier.size(32.dp),
                            tint = Red_A700
                        )
                    }

                    for (c in 0..4) {
                        Button(
                            onClick = { viewModel.nextColor(it, c) },
                            modifier = Modifier
                                .clip(CircleShape) // Make the button circular
                                .size(buttonContainerSize),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = clueColorMap.getValue(clueStatus[c].toString()),
                                contentColor = White
                            )
                        )
                        {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = clueWord[c].toString(),
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                )
                            }
                        }
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(iconContainerSize)
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Clue from list",
                            modifier = Modifier.size(32.dp),
                            tint = Red_A700
                        )
                    }
                }
            }
        }
        val scrollKey = clueCount //- 1 // TODO: Fix this //
        LaunchedEffect(key1 = scrollKey) {
            clueListState.animateScrollToItem(index = scrollKey)
        }

        Spacer(modifier = Modifier.height(32.dp))
//        Text(text = "Total number of Clues = $clueCount")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = typedClueText,
                onValueChange = { typedClueText = it.uppercase() },
                modifier = Modifier.width(210.dp),
                label = {
                    Text(
                        "Type New Clue Here",
                        color = Indigo_900,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    letterSpacing = 16.sp
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
            )
            Button(
                onClick = {
                    keyboardController?.hide()
                    viewModel.addClue(typedClueText, "BBBBB")
                    typedClueText = ""
                }
//                , colors = ButtonColors(
//                    containerColor = Indigo_900,
//                    contentColor = Indigo_50,
//                    disabledContainerColor = Indigo_50,
//                    disabledContentColor = Indigo_900)
            )
            {
                Text(text = "Add Clue", fontSize = 24.sp, fontWeight = FontWeight.Bold)

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
