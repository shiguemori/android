package br.com.shiguemori.ondeeh.model

data class Endereco(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String
)