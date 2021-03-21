package com.example.biografi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class Layout2 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout2)

        val btnMovetoDede: Button = findViewById(R.id.btn_dede)
        btnMovetoDede.setOnClickListener(this)

        val btnMovetoLuqman: Button = findViewById(R.id.btn_luqman)
        btnMovetoLuqman.setOnClickListener(this)

        val btnMovetoTaufan: Button = findViewById(R.id.btn_taufan)
        btnMovetoTaufan.setOnClickListener(this)

        val btnBacktoHome: Button = findViewById(R.id.btn_backHome)
        btnBacktoHome.setOnClickListener((this))
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_dede -> {
                val movetoBio = Intent(this@Layout2, BioDede::class.java)
                startActivity(movetoBio)
            }

            R.id.btn_luqman -> {
                val movetoBio = Intent(this@Layout2, BioLuqman::class.java)
                startActivity(movetoBio)
            }

            R.id.btn_taufan -> {
                val movetoBio = Intent(this@Layout2, BioTaufan::class.java)
                startActivity(movetoBio)
            }

            R.id.btn_backHome -> {
                finish()
            }
        }
    }

}