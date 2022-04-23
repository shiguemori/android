package br.com.shiguemori.listadecompras.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.shiguemori.listadecompras.models.RequestState
import br.com.shiguemori.listadecompras.models.User
import br.com.shiguemori.listadecompras.repository.UserRepository

class LoginViewModel(application: Application) :
    AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val loginState = MutableLiveData<RequestState<String>>()
    val loggedUserState = MutableLiveData<RequestState<String>>()

    fun login(user: User) {
        loginState.value = userRepository.login(user).value
    }

    fun getLoggedUser() {
        loggedUserState.value = userRepository.getLoggedUser().value
    }
}
//class LoginViewModel: ViewModel() {
//    private val userRepository = UserRepository()
//    val loginState = MutableLiveData<RequestState<String>>()
//    fun login(user: User) {
//        loginState.value = userRepository.login(user).value
//    }
//}