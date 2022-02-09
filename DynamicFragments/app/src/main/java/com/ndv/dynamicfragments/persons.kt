package com.ndv.dynamicfragments

val PERSONS = arrayOf("Manwë", "Varda", "Ulmo", "Yavanna", "Aulë", "Namo", "Nienna", "Oromë", "Irmo", "Nienna", "Nessa", "Tulkas")

fun getRandomIndex (max: Int, min: Int  = 0): Int {
    return (min..max).random()
}

fun getRandomPerson(persons: Array<String> = PERSONS): String {
    val max = persons.size - 1;
    val position = getRandomIndex(max)
    return persons[position]
}