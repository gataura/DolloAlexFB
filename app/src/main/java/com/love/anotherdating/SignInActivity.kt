package com.love.anotherdating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignInActivity : AppCompatActivity() {

    lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton = findViewById(R.id.log_in_button)

        signInButton.setOnClickListener {
            startActivity(Intent(this@SignInActivity, ProfileActivity::class.java))
        }
    }
}
