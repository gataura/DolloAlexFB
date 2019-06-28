package com.love.anotherdating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class AnketaFirstActivity : AppCompatActivity() {

    lateinit var anketaButton: Button

    private lateinit var heightSeek: SeekBar
    private lateinit var weightSeek: SeekBar
    lateinit var heightText: TextView
    lateinit var weightText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anketa_first)

        heightSeek = findViewById(R.id.height_sbar)
        weightSeek = findViewById(R.id.weight_sbar)
        heightText = findViewById(R.id.enter_textView5_height)
        weightText = findViewById(R.id.enter_textView8_weight)
        anketaButton = findViewById(R.id.anketaFirstButton)

        weightSeek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                weightText.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                weightText.text = seekBar.progress.toString()
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                weightText.text = seekBar.progress.toString()
            }

        })

        heightSeek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                heightText.text = (seekBar.progress + 140).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                heightText.text = (seekBar.progress + 140).toString()
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                heightText.text = (seekBar.progress + 140).toString()
            }

        })

        anketaButton.setOnClickListener {
            startActivity(Intent(this@AnketaFirstActivity, LookingFor::class.java))
        }
    }
}
