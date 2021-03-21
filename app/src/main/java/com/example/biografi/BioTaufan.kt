package com.example.biografi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class BioTaufan : AppCompatActivity(), View.OnClickListener {
    lateinit var btnToLayout2: Button
    lateinit var tvSosmed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio_taufan)

        btnToLayout2 = findViewById(R.id.btn_toLay2_taufan)
        tvSosmed= findViewById(R.id.sosmed) as TextView
        btnToLayout2.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        finish()
    }

    override fun onResume() {
        super.onResume()
        tvSosmed.text = getSosmed()
    }

    override fun onDestroy() {
        Toast.makeText(this, "Kembali ke daftar anggota", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    fun getSosmed():String{
        val sosmed = arrayOf("Instagram : taufanbg",
            "Facebook : Taufan Bagus",
            "LinkedIn : Taufan Bagus Grahita",
            "Github : Taufan Bagus")

        val ranNumb = (0..4).random()
        return sosmed[ranNumb]
    }
}