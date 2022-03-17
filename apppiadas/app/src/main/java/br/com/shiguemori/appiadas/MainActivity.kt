package br.com.shiguemori.appiadas

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.shiguemori.appiadas.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showJoker()
        binding.btTellJoker.setOnClickListener {
            binding.btTellJoker.isEnabled = false
            showJoker()
            playSong()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.btTellJoker.isEnabled = true
            }, 3000)
        }
    }

    private fun playSong() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.badumtss)
        mediaPlayer.start()
    }

    fun showJoker() {
        val jokers = resources.getStringArray(R.array.jokers)
        val numberJoker = Random().nextInt(jokers.size)
        val joker = jokers[numberJoker]
        binding.tvJoker.text = joker
    }
}