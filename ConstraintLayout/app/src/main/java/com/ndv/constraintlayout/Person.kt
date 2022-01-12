package com.ndv.constraintlayout

enum class Races(val value: String) {
    ELF("elf"),
    DWARF("dwarf"),
    HUMAN("human"),
    MAIA("Maia"),
    VALA("Vala"),
    HOBBIT("hobbit")
}

data class Person(val name: String, val surname: String, val age: Int, val race: Races) {}

