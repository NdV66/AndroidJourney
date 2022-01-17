package com.ndv.constraintlayout

enum class Races(val value: String) {
    VALA("Vala"),
    MAIA("Maia"),
    ELF("Elf"),
    HUMAN("Human"),
}

data class Person(val name: String, val surname: String, val age: Int, val race: Races) {
    override fun toString(): String {
        return name
    }
}

