package com.example.e_linguistik.ui.aboutme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.e_linguistik.R

class AboutMe : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        val btnMovetoDede: Button = findViewById(R.id.btn_dede)
        btnMovetoDede.setOnClickListener(this)

        val btnMovetoLuqman: Button = findViewById(R.id.btn_luqman)
        btnMovetoLuqman.setOnClickListener(this)

        val btnMovetoTaufan: Button = findViewById(R.id.btn_taufan)
        btnMovetoTaufan.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_dede -> {
                val movetoBio = Intent(this@AboutMe, DetailBiografi::class.java)
                movetoBio.putExtra(DetailBiografi.EXTRA_INDEX, 2)
                startActivity(movetoBio)
            }

            R.id.btn_luqman -> {
                val movetoBio = Intent(this@AboutMe, DetailBiografi::class.java)
                movetoBio.putExtra(DetailBiografi.EXTRA_INDEX, 0)
                startActivity(movetoBio)
            }

            R.id.btn_taufan -> {
                val movetoBio = Intent(this@AboutMe, DetailBiografi::class.java)
                movetoBio.putExtra(DetailBiografi.EXTRA_INDEX, 1)
                startActivity(movetoBio)
            }

        }
    }

}