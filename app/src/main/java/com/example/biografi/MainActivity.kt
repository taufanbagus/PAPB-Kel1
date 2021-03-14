package com.example.biografi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMovetoLayout2: Button = findViewById(R.id.button)
        btnMovetoLayout2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val moveLayout2 = Intent(this@MainActivity, Layout2:: class.java)
        startActivity(moveLayout2)
    }
}