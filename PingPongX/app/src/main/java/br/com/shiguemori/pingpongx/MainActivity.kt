package br.com.shiguemori.pingpongx

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.shiguemori.pingpongx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUpExtras()

        setUpListeners()

        initViewModel()

        initObservers()
    }

    private fun initObservers() {
        mainViewModel.goalHome.observe(this) { goalHome ->
            binding.tvPlayerOneScore.text = "$goalHome"
        }
        mainViewModel.goalAway.observe(this) { goalAway ->
            binding.tvPlayerTwoScore.text = "$goalAway"
        }
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun finish() {
        val ret = Intent()
        ret.putExtra("retorno", "Obrigado!")
        setResult(RESULT_OK, ret)
        super.finish()
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, data:
        Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            //Verifica se existe o parâmetro retorno
            if (data?.hasExtra("retorno") == true) {
                Toast.makeText(
                    this,
                    data.extras?.getString("retorno"),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setUpExtras() {
        binding.tvPlayerOneName.text = intent.getStringExtra(KEY_RESULT_EXTRA_PLAYER_ONE_NAME)
        binding.tvPlayerTwoName.text = intent.getStringExtra(KEY_RESULT_EXTRA_PLAYER_TWO_NAME)
    }

    private fun setUpListeners() {
        btPlayerOneScore()
        btPlayerTwoScore()
        btRevenge()
        btFinishMatch()
    }

    private fun btPlayerOneScore() {
        binding.btPlayerOneScore.setOnClickListener {
            mainViewModel.goalHome()
        }
    }

    private fun btPlayerTwoScore() {
        binding.btPlayerTwoScore.setOnClickListener {
            mainViewModel.goalAway()
        }
    }

    private fun btRevenge() {
        binding.btRevenge.setOnClickListener {
            mainViewModel.resetGame()
        }
    }

    /**
     * Método que reseta o placar e o nome dos jogadores
     */
    private fun btFinishMatch() {
        binding.btFinishMatch.setOnClickListener {
            val ret = Intent()
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_ONE_NAME, binding.tvPlayerOneName.text.toString())
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_TWO_NAME, binding.tvPlayerTwoName.text.toString())
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_ONE_SCORE, mainViewModel.goalHome.value)
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_TWO_SCORE, mainViewModel.goalAway.value)
            setResult(RESULT_OK, ret)
            super.finish()
        }
    }

}