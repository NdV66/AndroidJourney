package com.ndv.dynamicfragments

val PERSONS = arrayOf(
    "Manwë (1)",
    "Varda (2)",
    "Ulmo (3)",
    "Yavanna (4)",
    "Aulë (5)",
    "Namo (6)",
    "Nienna (7)",
    "Oromë (8)",
    "Irmo (9)",
    "Nienna (10)",
    "Nessa (11)",
    "Tulkas (12)")


var lastPosition = -1

fun getPerson(persons: Array<String> = PERSONS): String {
    val max = persons.size - 1
    lastPosition += 1

    if (lastPosition == max) {
        lastPosition = 0
    }

    return persons[lastPosition]
}