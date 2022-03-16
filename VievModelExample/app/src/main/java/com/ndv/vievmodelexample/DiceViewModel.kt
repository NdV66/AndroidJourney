package com.ndv.vievmodelexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val MIN_DICE = 1

class DiceViewModel : ViewModel() {
    init {
        Log.d("DiceRoll", "DiceViewModel created!")
    }

    private var _rollResult = MutableLiveData(0)
    private var _rollsCount = MutableLiveData(0)

    val rollResult: LiveData<Int>
        get() = _rollResult

    val rollsCount: LiveData<Int>
        get() = _rollsCount

    fun rollDice(dice: Int) {
        _rollResult.value = (MIN_DICE until dice).random()
        _rollsCount.value = _rollsCount.value?.plus(1)
    }
}
