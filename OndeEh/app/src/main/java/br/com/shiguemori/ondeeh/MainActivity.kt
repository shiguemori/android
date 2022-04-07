package br.com.shiguemori.ondeeh

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.shiguemori.ondeeh.databinding.ActivityMainBinding
import br.com.shiguemori.ondeeh.model.MainViewModel
import br.com.shiguemori.ondeeh.model.ViewState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setContentView(binding.root)

        binding.btPesquisar.setOnClickListener {
            mainViewModel.pesquisar(binding.etCep.text.toString())
        }

        registrarObserver()
    }

    private fun registrarObserver() {
        mainViewModel.enderecoResponse.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is ViewState.Success -> {
                    binding.tvLogradouro.text = it.data.logradouro
                    binding.tvBairro.text = it.data.bairro
                    binding.tvLocalidade.text = it.data.localidade
                    binding.tvUF.text = it.data.uf
                    binding.loadingLayout.visibility = View.GONE
                }
                is ViewState.Error -> {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                    binding.tvLogradouro.text = "N/A"
                    binding.tvBairro.text = "N/A"
                    binding.tvLocalidade.text = "N/A"
                    binding.tvUF.text = "N/A"
                    binding.loadingLayout.visibility = View.GONE
                }
                is ViewState.Loading -> {
                    binding.loadingLayout.visibility = View.VISIBLE
                }
            }
        })
    }
}