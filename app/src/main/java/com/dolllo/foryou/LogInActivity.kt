package com.dolllo.foryou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LogInActivity : AppCompatActivity() {

    lateinit var signInBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        signInBtn = findViewById(R.id.signIn_btn)

        signInBtn.setOnClickListener {
            startActivity(Intent(this@LogInActivity, UserProfileActivity::class.java))
        }
    }
}
