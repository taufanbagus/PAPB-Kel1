package com.example.e_linguistik.ui.kbbi

import android.app.Application
import androidx.lifecycle.*
import com.example.e_linguistik.db.HistoryDatabaseDao
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.ui.translator.TranslatorViewModel
import kotlinx.coroutines.launch

class KbbiViewModel(
        val database: HistoryDatabaseDao,
        application: Application):AndroidViewModel(application){

    fun insert(history: HistoryModel) {
        viewModelScope.launch {
            val check = database.getSpecificValue(history.originWord, "KBBI")
            if (check == null){
                database.insert(history)
            } else {
                database.deletSpecificValue(history.originWord)
                database.insert(history)
            }
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