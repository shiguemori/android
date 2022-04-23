package br.com.shiguemori.listadecompras.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.shiguemori.listadecompras.R
import br.com.shiguemori.listadecompras.databinding.ActivityLoginBinding
import br.com.shiguemori.listadecompras.models.RequestState
import br.com.shiguemori.listadecompras.models.User
import br.com.shiguemori.listadecompras.ui.NewProductActivity
import br.com.shiguemori.listadecompras.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var animacaoDoMascote: Animation
    private lateinit var animacaoDoFormulario: Animation
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAnimation()
        hideKeyboard()
        initListener()
        initViewModel()
        initObserver()

        loginViewModel.getLoggedUser()
    }

    private fun initListener() {
        binding.etPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.ivLogin.speed = 2f
                binding.ivLogin.setMinAndMaxProgress(0.0f, 0.65f)
            } else {
                binding.ivLogin.speed = 1f
                binding.ivLogin.setMinAndMaxProgress(0.65f, 1.0f)
            }
            binding.ivLogin.playAnimation()
        }

        binding.btLogin.setOnClickListener {
            loginViewModel.login(
                User(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            )
        }
    }

    private fun initObserver() {
        loginViewModel.loginState.observe(this, Observer {
            when (it) {
                is RequestState.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is RequestState.Error -> {
                    val animShake = AnimationUtils.loadAnimation(
                        this,
                        R.anim.shake
                    )
                    binding.containerLogin.startAnimation(animShake)
                    binding.tvPasswordFeedback.text = it.exception.message
                }
                is RequestState.Loading -> {
                }
            }
        })

        loginViewModel.loggedUserState.observe(this, Observer {
            when (it) {
                is RequestState.Success -> {
                    binding.etEmail.setText(it.data)
                }
                is RequestState.Error -> {
                }
                is RequestState.Loading -> {
                }
            }
        })
    }

    private fun initViewModel() {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun initAnimation() {
        animacaoDoMascote = AnimationUtils.loadAnimation(this, R.anim.frombottom2)
        animacaoDoFormulario = AnimationUtils.loadAnimation(this, R.anim.frombottom)
        binding.ivLogin.clearAnimation()
        binding.containerLogin.clearAnimation()
        binding.containerLogin.startAnimation(animacaoDoFormulario)
        binding.ivLogin.startAnimation(animacaoDoMascote)
    }

    private fun hideKeyboard() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }
}