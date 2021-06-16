package com.fdhasna21.multimedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        //as splash screen

        supportActionBar?.title = "Splash Screen"
        val timer = object : Thread() {
            override fun run() {
                try {
                    synchronized(this) { sleep(5000) }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@Activity2, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        timer.start()
    }
}