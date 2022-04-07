package br.com.shiguemori.pokedex.data.remote

import br.com.shiguemori.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("api/v2/pokemon/{pokemon}")
    fun pesquisar(@Path("pokemon") pokemon: String): Call<Pokemon>
}