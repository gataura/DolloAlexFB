package com.dolllo.foryou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WhatRLookingFor : AppCompatActivity() {

    lateinit var whoRlokoForBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_r_looking_for)

        whoRlokoForBtn = findViewById(R.id.looking_for_button)

        whoRlokoForBtn.setOnClickListener {
            startActivity(Intent(this@WhatRLookingFor, UserProfileActivity::class.java))
        }
    }
}
