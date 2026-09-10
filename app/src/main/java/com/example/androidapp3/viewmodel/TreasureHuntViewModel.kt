package com.example.androidapp3.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androidapp3.data.BusinessData
import com.example.androidapp3.model.Business

class TreasureHuntViewModel : ViewModel() {

    private val _currentIndex = mutableStateOf(0)

    val currentIndex: State<Int> = _currentIndex

    val businesses: List<Business> = BusinessData.businesses

    private val _visitedBusinessIds = mutableStateListOf<Int>()

    val visitedBusinessIds: List<Int>
        get() = _visitedBusinessIds

    private var progressLoaded = false

    fun loadProgress(context: Context) {

        if (progressLoaded) {
            return
        }

        val sharedPreferences = context.getSharedPreferences(
            "treasure_hunt_preferences",
            Context.MODE_PRIVATE
        )

        val savedIndex = sharedPreferences.getInt(
            "current_index",
            0
        )

        _currentIndex.value = savedIndex.coerceIn(
            0,
            businesses.lastIndex
        )

        val savedVisitedIds = sharedPreferences.getStringSet(
            "visited_business_ids",
            emptySet()
        ) ?: emptySet()

        _visitedBusinessIds.clear()

        savedVisitedIds.forEach { id ->

            id.toIntOrNull()?.let { businessId ->

                if (businesses.any { it.id == businessId }) {
                    _visitedBusinessIds.add(businessId)
                }
            }
        }

        progressLoaded = true
    }

    fun confirmVisit(context: Context) {

        val currentBusiness = businesses[_currentIndex.value]

        if (!_visitedBusinessIds.contains(currentBusiness.id)) {
            _visitedBusinessIds.add(currentBusiness.id)
        }

        if (_currentIndex.value < businesses.lastIndex) {
            _currentIndex.value = _currentIndex.value + 1
        }

        saveProgress(context)
    }

    private fun saveProgress(context: Context) {

        val sharedPreferences = context.getSharedPreferences(
            "treasure_hunt_preferences",
            Context.MODE_PRIVATE
        )

        sharedPreferences.edit()
            .putInt(
                "current_index",
                _currentIndex.value
            )
            .putStringSet(
                "visited_business_ids",
                _visitedBusinessIds
                    .map { it.toString() }
                    .toSet()
            )
            .apply()
    }

    fun isVisited(businessId: Int): Boolean {

        return _visitedBusinessIds.contains(businessId)
    }

    fun isLastBusiness(): Boolean {

        return _currentIndex.value == businesses.lastIndex
    }

    fun getProgress(): Int {

        return _visitedBusinessIds.size
    }

    fun getProgressPercentage(): Int {

        if (businesses.isEmpty()) {
            return 0
        }

        return (
                _visitedBusinessIds.size.toFloat() /
                        businesses.size.toFloat() *
                        100
                ).toInt()
    }

    fun resetGame(context: Context) {

        _currentIndex.value = 0

        _visitedBusinessIds.clear()

        val sharedPreferences = context.getSharedPreferences(
            "treasure_hunt_preferences",
            Context.MODE_PRIVATE
        )

        sharedPreferences.edit()
            .clear()
            .apply()
    }
}