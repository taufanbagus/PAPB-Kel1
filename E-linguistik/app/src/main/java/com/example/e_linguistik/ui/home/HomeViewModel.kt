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

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    lateinit var listData : List<String>
    //var listData = ArrayList<String>()

    var dataResultTrans = MutableLiveData<String>()
    var dataResultKbbi = MutableLiveData<String>()

    val translatorClient = Dependencies().TranslatorKoin
    val kbbiClient = Dependencies().kbbiKoin

    fun translateWord(input: String){
        viewModelScope.launch{
            val resultTranslatorData = translatorClient.getData("google", input, "en")
            resultTranslatorData.enqueue(object : Callback<TranslatorData?> {
                override fun onFailure(call: Call<TranslatorData?>, t: Throwable) {
                    Log.e("translatorData", "onFailure: "+t.message)
                }

                override fun onResponse(call: Call<TranslatorData?>, response: Response<TranslatorData?>) {
                    val res = response.body()!!
                    Log.e("cek",input)
                    //tvTransWordToday.text = res.data.result
                    Log.e("cek",res.data.result)
                    dataResultTrans.value = res.data.result
                }
            })
        }
    }

    fun translateUsingKBBI(input: String){
        viewModelScope.launch {
            val resulKbbiData = kbbiClient.getData("json", input)
            resulKbbiData.enqueue(object : Callback<KbbiData?> {
                override fun onFailure(call: Call<KbbiData?>, t: Throwable) {
                    Log.e("kbbiData", "onFailure: "+t.message)
                }

                override fun onResponse(call: Call<KbbiData?>, response: Response<KbbiData?>) {
                    val res = response.body()!!
                    val kbbistringBuilder = StringBuilder()
                    val arrDef = res.kateglo.definition
                    for(kbbiDef in arrDef){
                        kbbistringBuilder.append(kbbiDef.def_text)
                        kbbistringBuilder.append(".\n")
                    }
                    Log.e("cek kbbi",input)
                    Log.e("cek kbbi",kbbistringBuilder.toString())
                    dataResultKbbi.value = kbbistringBuilder.toString()
                }
            })
        }
    }

    fun readDataToDay(activity: MainActivity){
        //val fileName = "corpus.txt"
        //val inputString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val bufferedReader: BufferedReader = File("src/main/assets/corpus.txt").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }

        //val label: MutableList<String> = ArrayList()
        //val reader = BufferedReader(InputStreamReader(activity.getAssets().open("corpus.txt")))
        //var line: String
        //while (reader.readLine().also { line = it } != null) {
        //    listData.add(line)
        //}
        //reader.close()
        listData= inputString.split("\n")
        //fun readAsset(context: Context, fileName: String): String =
        //    context
        //        .assets
        //        .open(fileName)
        //        .bufferedReader()
        //        .use(BufferedReader::readText)
    }

    fun getDataToDay(){
        val ranNum = (0..79039).random()
        translateWord(listWordGroupData[ranNum].split(" ")[0])
        translateUsingKBBI(listWordGroupData[ranNum].split(" ")[0])
    }

}