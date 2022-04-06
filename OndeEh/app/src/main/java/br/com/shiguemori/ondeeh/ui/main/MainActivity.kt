package br.com.shiguemori.ondeeh.ui.main

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.shiguemori.ondeeh.databinding.ActivityMainBinding
import br.com.shiguemori.ondeeh.model.Endereco


class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(this)

        binding.btPesquisar.setOnClickListener {
            mainPresenter.pesquisar(binding.etCep.text.toString())
        }
    }

    override fun mostrarEndereco(endereco: Endereco?) {
        binding.tvLogradouro.text = endereco?.logradouro
        binding.tvBairro.text = endereco?.bairro
        binding.tvLocalidade.text = endereco?.localidade
        binding.tvUF.text = endereco?.uf
    }

    override fun mostrarErro(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
        binding.tvLogradouro.text = ""
        binding.tvBairro.text = ""
        binding.tvLocalidade.text = ""
        binding.tvUF.text = ""
    }

}