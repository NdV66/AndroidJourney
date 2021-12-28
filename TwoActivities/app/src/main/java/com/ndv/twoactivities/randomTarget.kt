package com.ndv.twoactivities

import android.text.Editable

private val targets = arrayOf("Pippin", "Frodo", "Mirkwood", "Gondor", "Rohan", "Gandalf", "Saruman")

private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

private fun getRandomNumber(max: Int) = (0..max).random()

fun getRandomTarget(): Editable {
    val index = getRandomNumber(targets.size - 1)
    val target = targets[index]
    return target.toEditable()
}

