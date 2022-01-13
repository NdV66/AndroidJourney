package com.ndv.constraintlayout

val maias = arrayOf(
    Person("Mairon", "Aulendil", 40000, Races.MAIA),
    Person("Olorin", "Manwendil", 40000, Races.MAIA),
    Person("Osse", "Ulmendil", 40000, Races.MAIA)
)

val elves = arrayOf(
    Person("Legolas", "-", 2000, Races.ELF),
    Person("Thranduil", "-", 40000, Races.ELF),
    Person("Elrond", "-", 40000, Races.ELF)
)

val humans = arrayOf(
    Person("Aragorn", "-", 100, Races.HUMAN),
    Person("Boromir", "-", 80, Races.HUMAN),
)

val valar = arrayOf(
    Person("Irmo", "Lorien", 40000, Races.VALA),
    Person("Namo", "Mnados", 80, Races.VALA),
)

val personsByRace = mapOf(
    Races.MAIA to maias,
    Races.ELF to elves,
    Races.HUMAN to humans,
    Races.VALA to valar
)

val races = arrayOf(Races.VALA, Races.ELF, Races.HUMAN)