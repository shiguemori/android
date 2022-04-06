package br.com.shiguemori.ondeeh.ui.main

import br.com.shiguemori.ondeeh.model.Endereco

interface MainContract {
    interface MainView {
        fun mostrarEndereco(endereco: Endereco?)
        fun mostrarErro(mensagem: String)
    }

    interface MainPresenter {
        fun pesquisar(cep: String)
    }
}
