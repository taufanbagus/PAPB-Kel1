package com.example.e_linguistik.ui.aboutme

import androidx.lifecycle.ViewModel
import com.example.e_linguistik.model.Biografi
import com.example.e_linguistik.data.BioData

class BiografiViewModel : ViewModel() {

    lateinit var listDataBio: ArrayList<Biografi>

    lateinit var detailNama: String
    lateinit var detailNim: String
    lateinit var detailAsal: String
    lateinit var detailTtl: String
    var detailFoto: Int = 0
    lateinit var detaiSosmed: Array<String>

    fun getBioDetail(index: Int){
        val list = ArrayList<Biografi>()
        list.addAll(BioData.listData)
        listDataBio = list
        val detailBio = listDataBio[index]

        detailNama = detailBio.name
        detailNim = detailBio.nim
        detailAsal = detailBio.asal
        detailTtl = detailBio.ttl
        detailFoto = detailBio.foto
        detaiSosmed = detailBio.sosmed
    }

}