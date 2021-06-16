package com.fdhasna21.multimedia

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_1.*

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Show Images"

        activity1_apple.setOnClickListener {
            activity1_apple.setBackgroundResource(R.color.redPrimary)
            activity_orange.setBackgroundColor(Color.TRANSPARENT)
            Toast.makeText(this, "This is an apple.", Toast.LENGTH_SHORT).show()
        }

        activity_orange.setOnClickListener {
            activity_orange.setBackgroundResource(R.color.redPrimary)
            activity1_apple.setBackgroundColor(Color.TRANSPARENT)
            Toast.makeText(this, "This is an orange.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}