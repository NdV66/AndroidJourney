package com.ndv.recyclerandcards

val VALAR = arrayOf(
    Person("Manwë"),
    Person("Varda"),
    Person("Ulmo"),
    Person("Yavanna"),
    Person("Aulë"),
    Person("Namo"),
    Person("Nienna"),
    Person("Oromë"),
    Person("Irmo"),
    Person("Nienna"),
    Person("Nessa"),
    Person("Tulkas"),
    Person("Vana"),
    Person("Este"))

val getPersonByName =
    {
            name: String,
            persons: Array<Person>,
        ->
        persons.find { it -> it.name == name }
    }