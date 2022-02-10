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
    "Tulkas (12)",
    "Vana (13)",
    "Este (14)")

fun getRandomIndex(max: Int, min: Int = 0): Int {
    return (min..max).random()
}

fun getPerson(persons: Array<String> = PERSONS): String {
    val max = persons.size - 1
    val position = getRandomIndex(max)
    return persons[position]
}