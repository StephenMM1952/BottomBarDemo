package com.example.bottombardemo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var clueCount by mutableIntStateOf(1)
    var clueWordList by mutableStateOf(listOf("ADIEU"))
    var clueStatusList by mutableStateOf(listOf("BBBBB"))

    fun addClue(newClueWord: String, newClueStatus: String){
        clueCount++
        clueWordList = clueWordList + newClueWord
        clueStatusList = clueStatusList + newClueStatus
    }
//    fun removeClue(index: Int){
//        clueCount--
//        clueWordList = clueWordList.filterIndexed { i, _ -> i != index }
//        clueStatusList = clueStatusList.filterIndexed { i, _ -> i != index }
//    }
//    fun clearClues(){
//        clueCount = 0
//        clueWordList = emptyList()
//        clueStatusList = emptyList()
//    }
//    fun reset(){
//        clueCount = 1
//        clueWordList = listOf("ADIEU")
//        clueStatusList = listOf("BBBBB")
//    }
}

