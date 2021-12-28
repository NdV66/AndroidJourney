package com.ndv.nameselector

private val elfNames = arrayOf("Elrond", "Thranduil", "Legolas", "Gil-Galad")
private val dwarfNames = arrayOf("Gimli", "Gloin", "Fili")
private val humanNames = arrayOf("Aragorn", "Boromir", "Faramir")
private val hobbitNames = arrayOf("Frodo", "Sam", "Pippin", "Merry")
private val wizardNames = arrayOf("Olorin/Gandalf", "Mairon/Sauron", "Curumo/Saruman")

private enum class RACES(val displayValue: String) {
    ELF("elf"),
    DWARF("dwarf"),
    HUMAN("human"),
    HOBBIT("hobbit"),
    WIZARD("wizard")
}

private val allNames = mapOf(
    RACES.ELF.displayValue to elfNames,
    RACES.DWARF.displayValue to dwarfNames,
    RACES.HUMAN.displayValue to humanNames,
    RACES.HOBBIT.displayValue to hobbitNames,
    RACES.WIZARD.displayValue to wizardNames
)

private fun getRandomNumber(min: Int = 0, max: Int) = (min..max).random()

fun selectNameByCategory(category: String): String {
    val selectedNames = allNames[category]!!
    val maxIndex = selectedNames.size - 1
    val index = getRandomNumber(max = maxIndex)

    return selectedNames[index]
}
