package com.example.bottombardemo.screens

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
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottombardemo.ui.theme.*
import com.example.bottombardemo.viewmodels.HomeViewModel

val clueColorMap = mapOf("B" to Grey_700, "G" to Green_700, "Y" to Yellow_900)

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Home(viewModel: HomeViewModel = viewModel()) {
    val clueCount = viewModel.clueCount
    val clueWordList = viewModel.clueWordList
    val clueStatusList = viewModel.clueStatusList

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
        HorizontalDivider(thickness = 1.dp)

        LazyColumn(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxHeight(.7f),
//                .background(color = Grey_200),
            state = clueListState, verticalArrangement = Arrangement.Bottom
        ) {

            items(clueCount) {

                val clueWord = clueWordList[it]
                val clueStatus = clueStatusList[it]

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier.size(iconContainerSize)
                    ) {
                        Icon(
                            imageVector = Icons.TwoTone.Delete,
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
                        ) {
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
                        onClick = { viewModel.removeClue(it) },
                        modifier = Modifier.size(iconContainerSize)
                    ) {
                        Icon(
                            imageVector = Icons.TwoTone.Clear,
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

        HorizontalDivider(thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = typedClueText,
                onValueChange = { typedClueText = it.uppercase() },
                modifier = Modifier.width(210.dp),
                label = {
                    Text(
                        "Type New Word Here",
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
            Button(onClick = {
                keyboardController?.hide()
                viewModel.addClue(typedClueText, "BBBBB")
                typedClueText = ""
            }) {
                Text(text = "Add Word to Clues", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }

        HorizontalDivider(thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(onClick = { // TODO  //
            }) {
                Text(
                    text = "Add Word to Database", fontSize = 16.sp, fontWeight = FontWeight.Medium
                )
            }
            Button(onClick = { viewModel.reset() }) {
                Text(
                    text = "Reset Clue List",
                    modifier = Modifier.padding(12.dp, 0.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

        }

        HorizontalDivider(thickness = 1.dp)
    }
}

