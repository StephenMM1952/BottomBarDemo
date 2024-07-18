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
        if(newClueWord.length != 5) return
        clueCount++
        clueWordList = clueWordList + newClueWord
        clueStatusList = clueStatusList + newClueStatus
    }

    fun nextColor(it: Int, c: Int) {

        var clueStatus = clueStatusList[it]

        if (clueStatus[c] == 'B') {
            clueStatus = clueStatus.replaceRange(c, c + 1, "Y")
        } else if (clueStatus[c] == 'Y') {
            clueStatus = clueStatus.replaceRange(c, c + 1, "G")
        } else {
            clueStatus = clueStatus.replaceRange(c, c + 1, "B")
        }

        clueStatusList = clueStatusList.toMutableList().apply {
            set(it, clueStatus)
        }
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

