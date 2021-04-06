package com.example.e_linguistik

import com.example.e_linguistik.data.BioData

class BioModel {
    private val list = ArrayList<Biografi>()

    fun addData(): ArrayList<Biografi> {
        list.addAll(BioData.listData)
        return list
    }
}