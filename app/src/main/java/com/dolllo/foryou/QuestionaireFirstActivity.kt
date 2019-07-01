package com.dolllo.foryou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class QuestionaireFirstActivity : AppCompatActivity() {

    lateinit var questionaireBtn: Button

    private lateinit var seekHeight: SeekBar
    private lateinit var seekWeight: SeekBar
    lateinit var textHeight: TextView
    lateinit var textWeight: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionaire_first)

        seekHeight = findViewById(R.id.height_seekBar)
        seekWeight = findViewById(R.id.weight_seekBar)
        textHeight = findViewById(R.id.textView5_height_field)
        textWeight = findViewById(R.id.textView8_weight_field)
        questionaireBtn = findViewById(R.id.questionaireFirstBut)

        seekWeight.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                textWeight.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textWeight.text = seekBar.progress.toString()
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textWeight.text = seekBar.progress.toString()
            }

        })

        seekHeight.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                textHeight.text = (seekBar.progress + 140).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                textHeight.text = (seekBar.progress + 140).toString()
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textHeight.text = (seekBar.progress + 140).toString()
            }

        })

        questionaireBtn.setOnClickListener {
            startActivity(Intent(this@QuestionaireFirstActivity, WhatRLookingFor::class.java))
        }
    }
}
