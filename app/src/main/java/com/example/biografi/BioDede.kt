package com.example.biografi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class BioDede : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio_dede)

        val btnBackToLayout2 : Button = findViewById(R.id.btn_toLay2_dede)
        btnBackToLayout2.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        finish()
    }
}