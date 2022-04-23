package br.com.shiguemori.listadecompras.models

sealed class RequestState<out T> {
    object Loading : RequestState<Nothing>()
    class Success<T>(val data: T) : RequestState<T>()
    class Error(val exception: Exception) : RequestState<Nothing>()
}