package com.ndv.sharedviewmodel


enum class FRAGMENTS {
    NAME,
    METAl,
    SUMMARY
}

fun interface IStepperFragmentListener {
    fun onClick()
}

