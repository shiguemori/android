package br.com.shiguemori.ondeeh.model

data class Endereco(
    val cep: String = "N/A",
    val logradouro: String = "N/A",
    val complemento: String = "N/A",
    val bairro: String = "N/A",
    val localidade: String = "N/A",
    val uf: String = "N/A"
)