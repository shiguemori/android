package br.com.shiguemori.ondeeh.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.shiguemori.ondeeh.data.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val enderecoResponse = MutableLiveData<ViewState<Endereco>>()

    fun pesquisar(cep: String) {
        enderecoResponse.value = ViewState.Loading

        APIService.instance
            ?.pesquisar(cep)
            ?.enqueue(object : Callback<Endereco> {
                override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                    if (response.isSuccessful) {
                        enderecoResponse.value = ViewState.Success(response.body() ?: Endereco())
                    } else {
                        enderecoResponse.value = ViewState.Error(Throwable("Erro ao pesquisar"))
                    }
                }

                override fun onFailure(call: Call<Endereco>, t: Throwable) {
                    enderecoResponse.value = ViewState.Error(t)
                }
            })
    }
}