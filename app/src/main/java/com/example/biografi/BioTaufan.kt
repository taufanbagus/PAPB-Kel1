package com.example.biografi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class BioTaufan : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio_taufan)

        val btnBackLayout2: Button = findViewById(R.id.btn_toLay2_taufan)
        btnBackLayout2.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        finish()
    }
}