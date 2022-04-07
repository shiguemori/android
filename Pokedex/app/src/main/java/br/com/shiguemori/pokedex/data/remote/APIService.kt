package br.com.shiguemori.pokedex.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIService {

    private var INSTANCE: PokemonService? = null

    val instance: PokemonService?
        get() {
            if (INSTANCE == null) {
                val client = OkHttpClient.Builder()
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(client)
                    .build()
                INSTANCE = retrofit.create(PokemonService::class.java)
            }
            return INSTANCE
        }
}