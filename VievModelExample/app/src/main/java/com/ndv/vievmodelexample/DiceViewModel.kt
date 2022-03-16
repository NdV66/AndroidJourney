package com.ndv.vievmodelexample

import android.util.Log
import androidx.lifecycle.ViewModel

private const val MIN_DICE = 1

class DiceViewModel : ViewModel() {
    init {
        Log.d("DiceRoll", "DiceViewModel created!")
    }

    private var _rollResult = 0
    private var _rollsCount = 0

    val rollResult: Int
        get() = _rollResult

    val rollsCount: Int
        get() = _rollsCount

    fun rollDice(dice: Int) {
        _rollResult =  (MIN_DICE until dice).random()
        _rollsCount++
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("DiceRoll", "DiceViewModel destroyed!")
    }
}
