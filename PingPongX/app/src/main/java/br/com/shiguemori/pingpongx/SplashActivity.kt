package br.com.shiguemori.pingpongx

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import br.com.shiguemori.pingpongx.databinding.ActivitySplashBinding

/**
 * @author shiguemori
 * @since Sunday,  1/31/2020
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAnimation()
        nextScreen()
    }

    /**
     * Inicia a animação
     */
    private fun initAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash)
        binding.ivLogo.startAnimation(anim)
        binding.ivLogoName.startAnimation(anim)
    }

    /**
     * Método para chamar a próxima tela
     */
    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

}