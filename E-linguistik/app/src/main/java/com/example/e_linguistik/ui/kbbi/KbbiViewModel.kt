package com.example.e_linguistik.ui.kbbi

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.e_linguistik.DI.Dependencies
import com.example.e_linguistik.data.WordGroup
import com.example.e_linguistik.db.HistoryDatabaseDao
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.ui.kbbi.model.KbbiData
import com.example.e_linguistik.ui.translator.TranslatorViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KbbiViewModel(
        val database: HistoryDatabaseDao,
        application: Application):AndroidViewModel(application){

    val kbbiClient = Dependencies().kbbiKoin
    var dataResultKbbi = MutableLiveData<String>()

    fun insert(history: HistoryModel) {
        viewModelScope.launch {
            val check = database.getSpecificValue(history.originWord, "KBBI")
            if (check == null){
                database.insert(history)
            } else {
                database.deletSpecificValue(history.originWord, "KBBI")
                database.insert(history)
            }
        }
    }

    fun translateUsingKBBI(word:String){
        viewModelScope.launch {
            val resulKbbiData = kbbiClient.getData("json", word)
            resulKbbiData.enqueue(object : Callback<KbbiData?> {
                override fun onFailure(call: Call<KbbiData?>, t: Throwable) {
                    Log.e("kbbiData", "onFailure: "+t.message)
                }

                override fun onResponse(call: Call<KbbiData?>, response: Response<KbbiData?>) {
                    val res = response.body()!!
                    val kbbistringBuilder = StringBuilder()
                    val arrDef = res.kateglo.definition
                    var i = 1
                    for(kbbiDef in arrDef){
                        kbbistringBuilder.append(i.toString() + ". " + kbbiDef.def_text)
                        kbbistringBuilder.append(".\n")
                        kbbistringBuilder.append("\n")
                        i = i+1
                    }
                    Log.e("cek kbbi", word)
                    dataResultKbbi.value = kbbistringBuilder.toString()
                }
            })
        }
    }

}

class KBBIViewModelFactory(
    private val dataSource: HistoryDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KbbiViewModel::class.java)) {
            return KbbiViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}