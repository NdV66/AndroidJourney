package com.ndv.fragments

enum class RACES(val printableName: String)  {
    ELF("elf")
}

data class Person(val name: String, val race: RACES,  val description: String) {}