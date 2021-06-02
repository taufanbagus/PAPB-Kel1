package com.example.e_linguistik.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_linguistik.DI.Dependencies
import com.example.e_linguistik.MainActivity
import com.example.e_linguistik.data.WordGroup.listWordGroupData
import com.example.e_linguistik.ui.kbbi.model.KbbiData
import com.example.e_linguistik.ui.translator.model.TranslatorData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class HomeViewModel : ViewModel() {

    var dataTransToDay = MutableLiveData<String>()
    var dataKbbiToDay = MutableLiveData<String>()

    var dataResultTrans = MutableLiveData<String>()
    var dataResultKbbi = MutableLiveData<String>()

    val translatorClient = Dependencies().TranslatorKoin
    val kbbiClient = Dependencies().kbbiKoin

    fun translateWord(number: Int){
        viewModelScope.launch{
            dataTransToDay.value = listWordGroupData[number]
            val resultTranslatorData = translatorClient.getData("google", listWordGroupData[number], "en")
            resultTranslatorData.enqueue(object : Callback<TranslatorData?> {
                override fun onFailure(call: Call<TranslatorData?>, t: Throwable) {
                    Log.e("translatorData", "onFailure: "+t.message)
                }

                override fun onResponse(call: Call<TranslatorData?>, response: Response<TranslatorData?>) {
                    val res = response.body()!!
                    Log.e("cek",listWordGroupData[number])
                    //tvTransWordToday.text = res.data.result
                    Log.e("cek",res.data.result)
                    dataResultTrans.value = res.data.result
                }
            })
        }
    }

    fun translateUsingKBBI(number: Int){
        viewModelScope.launch {
            dataKbbiToDay.value = listWordGroupData[number]
            val resulKbbiData = kbbiClient.getData("json", listWordGroupData[number])
            resulKbbiData.enqueue(object : Callback<KbbiData?> {
                override fun onFailure(call: Call<KbbiData?>, t: Throwable) {
                    Log.e("kbbiData", "onFailure: "+t.message)
                    if (t.message.toString() == "Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path $"){
                        val ranNum = (0..13768).random()
                        translateUsingKBBI(ranNum)
                    }
                }

                override fun onResponse(call: Call<KbbiData?>, response: Response<KbbiData?>) {
                    val res = response.body()!!
                    val kbbistringBuilder = StringBuilder()
                    val arrDef = res.kateglo.definition
                    for(kbbiDef in arrDef){
                        kbbistringBuilder.append(kbbiDef.def_text)
                        kbbistringBuilder.append(".\n")
                    }
                    Log.e("cek kbbi", listWordGroupData[number])
                    Log.e("cek kbbi",kbbistringBuilder.toString())
                    dataResultKbbi.value = kbbistringBuilder.toString()
                }
            })
        }
    }


    fun getDataToDay(){
        val ranNum = (0..13768).random()

        Log.e("Random number", ranNum.toString())

        translateWord(ranNum)
        translateUsingKBBI(ranNum)
    }

}