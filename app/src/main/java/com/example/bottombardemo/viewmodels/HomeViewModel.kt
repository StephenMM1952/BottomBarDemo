package com.example.bottombardemo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel(): ViewModel() {
    var clueCount by mutableIntStateOf(1)
    var clueList by mutableStateOf(listOf("ADIEU"))

    fun addClue(newClue: String){
        clueCount++
        clueList = clueList + newClue
    }
    fun removeClue(index: Int){
        clueCount--
        clueList = clueList.filterIndexed { i, _ -> i != index }
    }
    fun clearClues(){
        clueCount = 0
        clueList = emptyList()
    }
    fun reset(){
        clueCount = 1
        clueList = listOf("ADIEU")
    }


}