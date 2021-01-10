package com.gilangarinata.sehatqecommerce.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gilangarinata.sehatqecommerce.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            goToHome()
        }

        btn_google.setOnClickListener {
            goToHome()
        }

        btn_facebook.setOnClickListener {
            goToHome()
        }

    }

    private fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}