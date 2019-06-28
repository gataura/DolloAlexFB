package com.love.anotherdating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LookingFor : AppCompatActivity() {

    lateinit var lookingForButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looking_for)

        lookingForButton = findViewById(R.id.looking_for_button)

        lookingForButton.setOnClickListener {
            startActivity(Intent(this@LookingFor, ProfileActivity::class.java))
        }
    }
}
