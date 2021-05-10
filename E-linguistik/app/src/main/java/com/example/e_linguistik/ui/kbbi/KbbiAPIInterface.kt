package com.example.e_linguistik.ui.kbbi

import com.example.e_linguistik.ui.kbbi.model.KbbiData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KbbiAPIInterface {

    @GET("api.php")
    fun getData(@Query("format") formatText: String, @Query("phrase") word: String): Call<KbbiData>
//
//    @GET("api.php?format=json&phrase={word}")
//    Call<KbbiData> getData(@Path("member") word: String)

}