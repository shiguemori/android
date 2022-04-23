package br.com.shiguemori.listadecompras.models

data class User(val vEmail: String, val vPass: String) {
    val email: String = vEmail
    val password: String = vPass
}