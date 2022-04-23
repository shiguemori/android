package br.com.shiguemori.listadecompras.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.shiguemori.listadecompras.models.RequestState
import br.com.shiguemori.listadecompras.models.User

class UserRepository(val context: Context) {
    fun login(user: User): LiveData<RequestState<String>> {
        val response = MutableLiveData<RequestState<String>>()
        if (user.email == "teste@gmail.com" && user.password == "teste") {
            val pref = context.getSharedPreferences("lembretedecompras", 0)
            val editor = pref.edit()
            editor.putString("email", user.email)
            editor.apply()
            response.value = RequestState.Success("")
        } else {
            response.value = RequestState.Error(Exception("Usuário ou senha invalido"))
        }
        return response
    }

    fun getLoggedUser(): LiveData<RequestState<String>> {
        val response = MutableLiveData<RequestState<String>>()
        val pref = context.getSharedPreferences("lembretedecompras", 0)
        val email = pref.getString("email", "") ?: ""
        response.value = RequestState.Success(email)
        return response
    }
}

//class UserRepository {
//    fun login(user: User): LiveData<RequestState<String>> {
//        val response = MutableLiveData<RequestState<String>>()
//        if (user.email == "compra@facil.com.br" &&
//            user.password == "123456"
//        ) {
//            response.value = RequestState.Success("")
//        } else {
//            response.value = RequestState.Error(Exception("Usuário ou senha invalido"))
//        }
//        return response
//    }
//}