package com.example.e_linguistik.ui.translator

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.e_linguistik.DI.Dependencies
import com.example.e_linguistik.data.WordGroup
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.db.HistoryDatabaseDao
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.ui.translator.model.TranslatorData

import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TranslatorViewModel(
        val database: HistoryDatabaseDao,
        application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    var dataResultTrans = MutableLiveData<String>()
    val translatorClient = Dependencies().TranslatorKoin

    fun insert(history: HistoryModel) {
        viewModelScope.launch {
            val check = database.getSpecificValue(history.originWord, "Translate")
            if (check == null){
                database.insert(history)
            } else {
                database.deletSpecificValue(history.originWord, "Translate")
                database.insert(history)
            }
        }
    }

    fun translateWord(word: String){
        viewModelScope.launch{
            val resultTranslatorData = translatorClient.getData("google", word, "en")
            resultTranslatorData.enqueue(object : Callback<TranslatorData?> {
                override fun onFailure(call: Call<TranslatorData?>, t: Throwable) {
                    Log.e("translatorData", "onFailure: "+t.message)
                }

                override fun onResponse(call: Call<TranslatorData?>, response: Response<TranslatorData?>) {
                    val res = response.body()!!
                    Log.e("cek", word)
                    dataResultTrans.value = res.data.result
                }
            })
        }
    }

}

class TranslatorViewModelFactory(
        private val dataSource: HistoryDatabaseDao,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TranslatorViewModel::class.java)) {
            return TranslatorViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

