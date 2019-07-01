package com.dollo.foryou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HowRULookingFor : AppCompatActivity() {

    lateinit var whoLookingFor: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_r_u_looking_for)

        whoLookingFor = findViewById(R.id.looking_for_button)

        whoLookingFor.setOnClickListener {
            startActivity(Intent(this@HowRULookingFor, UsProfileActivity::class.java))
        }
    }
}
