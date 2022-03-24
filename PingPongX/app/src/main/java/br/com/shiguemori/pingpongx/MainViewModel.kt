package br.com.shiguemori.pingpongx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by shiguemori.
 */
class MainViewModel : ViewModel() {
    val goalHome: MutableLiveData<Int> = MutableLiveData()
    val goalAway: MutableLiveData<Int> = MutableLiveData()

    init {
        startGame()
    }

    fun startGame() {
        goalHome.value = 0
        goalAway.value = 0
    }

    fun goalHome() {
        goalHome.value = goalHome.value?.plus(1)
    }

    fun goalAway() {
        goalAway.value = goalAway.value?.plus(1)
    }

    fun resetGame() {
        startGame()
    }


}