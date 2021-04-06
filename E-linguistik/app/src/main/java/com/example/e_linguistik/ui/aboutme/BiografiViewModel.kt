package com.example.e_linguistik.ui.aboutme

import androidx.lifecycle.ViewModel
import com.example.e_linguistik.BioModel
import com.example.e_linguistik.Biografi

class BiografiViewModel : ViewModel() {
    private var bioModel: BioModel = BioModel()

    private var listDataBio: ArrayList<Biografi> = bioModel.addData()

    lateinit var detailNama: String
    lateinit var detailNim: String
    lateinit var detailAsal: String
    lateinit var detailTtl: String
    var detailFoto: Int = 0
    lateinit var detaiSosmed: Array<String>

    fun getBioDetail(index: Int){
        val detailBio = listDataBio[index]

        detailNama = detailBio.name
        detailNim = detailBio.nim
        detailAsal = detailBio.asal
        detailTtl = detailBio.ttl
        detailFoto = detailBio.foto
        detaiSosmed = detailBio.sosmed
    }

    /*
    private val _nama = MutableLiveData<String>().apply {
        value = detailNama
    }

    val val_nama: LiveData<String> = _nama

    private val _nim = MutableLiveData<String>().apply {
        value = detailNim
    }

    val val_nim: LiveData<String> = _nim

    private val _asal = MutableLiveData<String>().apply {
        value = detailAsal
    }

    val val_asal: LiveData<String> = _asal

    private val _ttl = MutableLiveData<String>().apply {
        value = detailTtl
    }

    val val_ttl: LiveData<String> = _ttl

    private val _foto = MutableLiveData<Int>().apply {
        value = detailFoto
    }

    val val_foto: LiveData<Int> = _foto

    private val _sosmed = MutableLiveData<Array<String>>().apply {
        value = detaiSosmed
    }

    val val_sosmed: LiveData<Array<String>> = _sosmed
    */
}