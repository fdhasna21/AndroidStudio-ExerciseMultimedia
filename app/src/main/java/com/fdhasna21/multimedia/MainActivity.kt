package com.fdhasna21.multimedia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.fdhasna21.multimedia.Activity7.Activity7
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button1.setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, Activity3::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, Activity4::class.java))
        }

        button5.setOnClickListener {
            startActivity(Intent(this, Activity5::class.java))
        }

        button6.setOnClickListener {
            startActivity(Intent(this, Activity6::class.java))
        }

        button7.setOnClickListener {
            startActivity(Intent(this, Activity7::class.java))
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}