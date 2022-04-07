package br.com.shiguemori.pokedex.model

data class Pokemon(
    val name: String = "",
    val order: String = "",
    var sprites: Sprites = Sprites(),
)