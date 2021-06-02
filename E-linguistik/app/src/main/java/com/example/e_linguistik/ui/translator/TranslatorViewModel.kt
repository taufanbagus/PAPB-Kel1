package com.example.e_linguistik.ui.translator

import android.app.Application
import androidx.lifecycle.*
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.db.HistoryDatabaseDao
import com.example.e_linguistik.db.HistoryModel

import kotlinx.coroutines.launch

class TranslatorViewModel(
        val database: HistoryDatabaseDao,
        application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    fun insert(history: HistoryModel) {
        viewModelScope.launch {
            val check = database.getSpecificValue(history.originWord, "Translate")
            if (check == null){
                database.insert(history)
            } else {
                database.deletSpecificValue(history.originWord)
                database.insert(history)
            }
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

