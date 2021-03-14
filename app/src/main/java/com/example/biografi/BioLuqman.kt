package com.example.biografi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class BioLuqman : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio_luqman)

        val btnToLayout2: Button = findViewById(R.id.btn_toLay2_luqman)
        btnToLayout2.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        finish()
    }
}