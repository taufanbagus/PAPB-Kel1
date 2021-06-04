package com.example.e_linguistik.ui.translator

import com.example.e_linguistik.ui.kbbi.model.KbbiData
import com.example.e_linguistik.ui.translator.model.TranslatorData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslatorAPIInterface {
    @GET("translate")
    fun getData(@Query("engine") engine: String, @Query("text") text: String, @Query("to") word: String): Call<TranslatorData>
}