package ru.otus.animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tikTok = findViewById<TikTokAnimation>(R.id.tiktok)
        val ripple = findViewById<RippleAnimation>(R.id.ripple)

        tikTok.setOnClickListener {
            tikTok.animateCircles()
        }

        ripple.setOnClickListener {
            ripple.animateCircles()
        }
    }
}