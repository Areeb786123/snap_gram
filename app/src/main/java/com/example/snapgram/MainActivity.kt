package com.example.snapgram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.core.os.HandlerCompat.postDelayed

class MainActivity : AppCompatActivity() {




    lateinit var  handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        handler = Handler()
        handler.postDelayed(
            {
                val intent = Intent(this,LOGIN::class.java)
                startActivity(intent)
                finish()

            },2000
        )


    }
}