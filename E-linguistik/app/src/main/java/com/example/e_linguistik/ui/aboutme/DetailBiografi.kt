package com.example.e_linguistik.ui.aboutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_linguistik.R

class DetailBiografi : AppCompatActivity() {

    private lateinit var biografiViewModel: BiografiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_biografi)

        supportActionBar?.title = "About Me"

        val index = intent.getIntExtra(EXTRA_INDEX, 0)
        biografiViewModel = ViewModelProvider(this).get(BiografiViewModel::class.java)
        biografiViewModel.getBioDetail(index)

        var image: ImageView = findViewById(R.id.pict)
        var nama: TextView = findViewById(R.id.tv_nama)
        var nim: TextView = findViewById(R.id.tv_nim)
        var asal: TextView = findViewById(R.id.tv_asal)
        var ttl: TextView = findViewById(R.id.tv_ttl)
        var ig: TextView = findViewById(R.id.tv_ig)
        var fb: TextView = findViewById(R.id.tv_fb)
        var linkedin: TextView = findViewById(R.id.tv_linkedin)
        var github: TextView = findViewById(R.id.tv_github)

        nama.text = ": "  + biografiViewModel.detailNama
        nim.text = ": " + biografiViewModel.detailNim
        asal.text = ": " + biografiViewModel.detailAsal
        ttl.text = ": " + biografiViewModel.detailTtl
        ig.text = ": " + biografiViewModel.detaiSosmed[0]
        fb.text = ": " + biografiViewModel.detaiSosmed[1]
        linkedin.text = ": " + biografiViewModel.detaiSosmed[2]
        github.text = ": " + biografiViewModel.detaiSosmed[3]

        Glide.with(this)
                .load(biografiViewModel.detailFoto)
                .apply(RequestOptions().override(200,200))
                .into(image)

    }

    override fun onDestroy() {
        Toast.makeText(this, "Jangan lupa kunjungi sosial media kami", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    companion object{
        const val EXTRA_INDEX = "extra_index"
    }
}