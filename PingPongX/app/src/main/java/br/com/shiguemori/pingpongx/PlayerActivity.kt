package br.com.shiguemori.pingpongx

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.com.shiguemori.pingpongx.databinding.ActivityPlayerBinding

/**
 * @author shiguemori
 */
class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStart.setOnClickListener {
            val nextScreen = Intent(this, MainActivity::class.java)
            nextScreen.putExtra(KEY_RESULT_EXTRA_PLAYER_ONE_NAME, binding.etPlayer1.text.toString())
            nextScreen.putExtra(KEY_RESULT_EXTRA_PLAYER_TWO_NAME, binding.etPlayer2.text.toString())
            previewRequest.launch(nextScreen)
        }
    }

    private val previewRequest =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val lastResult = getString(
                    R.string.message_to_share,
                    it.data?.getStringExtra(KEY_RESULT_EXTRA_PLAYER_ONE_NAME),
                    it.data?.getStringExtra(KEY_RESULT_EXTRA_PLAYER_TWO_NAME),
                    it.data?.getIntExtra(KEY_RESULT_EXTRA_PLAYER_ONE_SCORE, 0),
                    it.data?.getIntExtra(KEY_RESULT_EXTRA_PLAYER_TWO_SCORE, 0)
                )
                binding.tvLastGame.text = lastResult
            }
        }
}