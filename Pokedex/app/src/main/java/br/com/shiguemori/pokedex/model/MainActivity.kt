package br.com.shiguemori.pokedex.model

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.com.shiguemori.pokedex.R
import br.com.shiguemori.pokedex.data.remote.APIService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var ivPokemonBack: ImageView
    private lateinit var ivPokemonFront: ImageView
    private lateinit var btnPesquisar: Button
    private lateinit var pokemonName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setUpView()
        setUpListener()

    }

    private fun setUpView() {
        ivPokemonBack = findViewById<ImageView>(R.id.ivPokemon1)
        ivPokemonFront = findViewById<ImageView>(R.id.ivPokemon2)
        btnPesquisar = findViewById<Button>(R.id.btnPesquisar)
        pokemonName = findViewById<EditText>(R.id.pokemonName)
        pesquisar()
    }

    private fun setUpListener() {
        btnPesquisar.setOnClickListener {
            pesquisar(pokemonName.text.toString())
        }
    }

    private fun pesquisar(name: String = "bulbasaur") {
        APIService.instance?.pesquisar(name)?.enqueue(object : Callback<Pokemon> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    findViewById<TextView>(R.id.tvPokemonName).text = response.body()?.order + " - " + response.body()?.name
                    Picasso.get().load(response.body()?.sprites?.back_default).into(ivPokemonBack)
                    Picasso.get()
                        .load(response.body()?.sprites?.front_default)
                        .into(ivPokemonFront)
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Erro ao carregar dados", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

}