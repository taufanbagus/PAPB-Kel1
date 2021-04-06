package com.example.e_linguistik.ui.aboutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_linguistik.R

class DetailBiografi : AppCompatActivity() {

    private lateinit var biografiViewModel: BiografiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_biografi)

        val index = intent.getIntExtra(EXTRA_INDEX, 0)
        biografiViewModel = ViewModelProvider(this).get(BiografiViewModel::class.java)
        biografiViewModel.getBioDetail(index)

        var image: ImageView = findViewById(R.id.pict)
        var nama: TextView = findViewById(R.id.tv_nama)
        var nim: TextView = findViewById(R.id.tv_nim)
        var asal: TextView = findViewById(R.id.tv_asal)
        var ttl: TextView = findViewById(R.id.tv_ttl)

        nama.text = ": "  + biografiViewModel.detailNama
        nim.text = ": " + biografiViewModel.detailNim
        asal.text = ": " + biografiViewModel.detailAsal
        ttl.text = ": " + biografiViewModel.detailTtl

        Glide.with(this)
                .load(biografiViewModel.detailFoto)
                .apply(RequestOptions().override(200,200))
                .into(image)

    }

    companion object{
        const val EXTRA_INDEX = "extra_index"
    }
}