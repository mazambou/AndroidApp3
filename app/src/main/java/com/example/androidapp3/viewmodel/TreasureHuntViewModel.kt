package com.example.androidapp3.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androidapp3.data.BusinessData
import com.example.androidapp3.model.Business

class TreasureHuntViewModel : ViewModel() {

    private val _currentIndex = mutableStateOf(0)

    val currentIndex: State<Int> = _currentIndex

    val businesses: List<Business> = BusinessData.businesses

    fun nextBusiness() {
        if (_currentIndex.value < businesses.size - 1) {
            _currentIndex.value = _currentIndex.value + 1
        }
    }

    fun resetGame() {
        _currentIndex.value = 0
    }

    fun isLastBusiness(): Boolean {
        return _currentIndex.value == businesses.size - 1
    }

    fun getProgress(): Int {
        return _currentIndex.value + 1
    }
}