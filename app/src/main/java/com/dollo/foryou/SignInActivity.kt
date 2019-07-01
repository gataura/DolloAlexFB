package com.dollo.foryou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignInActivity : AppCompatActivity() {

    lateinit var logInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        logInButton = findViewById(R.id.sign_in_button)

        logInButton.setOnClickListener {
            startActivity(Intent(this@SignInActivity, UsProfileActivity::class.java))
        }
    }
}
