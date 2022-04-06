package br.com.shiguemori.ondeeh

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.shiguemori.ondeeh.data.remote.APIService
import br.com.shiguemori.ondeeh.model.Endereco
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var etCEP: EditText
    private lateinit var btPesquisar: Button
    private lateinit var tvLogradouro: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvLocalidade: TextView
    private lateinit var tvUF: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpListener()
    }

    private fun setUpListener() {
        btPesquisar.setOnClickListener {
            pesquisar()
        }
    }

    private fun pesquisar() {
        APIService
            .instance
            ?.pesquisar(etCEP.text.toString())
            ?.enqueue(object : retrofit2.Callback<Endereco> {
                override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            tvLogradouro.text = it.logradouro
                            tvBairro.text = it.bairro
                            tvLocalidade.text = it.localidade
                            tvUF.text = it.uf
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "CEP não encontrado", Toast.LENGTH_SHORT)
                            .show()
                        tvLogradouro.text = "N/A"
                        tvBairro.text = "N/A"
                        tvLocalidade.text = "N/A"
                        tvUF.text = "N/A"
                    }
                }

                override fun onFailure(call: Call<Endereco>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                    tvLogradouro.text = "N/A"
                    tvBairro.text = "N/A"
                    tvLocalidade.text = "N/A"
                    tvUF.text = "N/A"
                }
            })
    }

    private fun setUpView() {
        etCEP = findViewById(R.id.etCep)
        btPesquisar = findViewById(R.id.btPesquisar)
        tvLogradouro = findViewById(R.id.tvLogradouro)
        tvBairro = findViewById(R.id.tvBairro)
        tvLocalidade = findViewById(R.id.tvLocalidade)
        tvUF = findViewById(R.id.tvUF)
    }
}