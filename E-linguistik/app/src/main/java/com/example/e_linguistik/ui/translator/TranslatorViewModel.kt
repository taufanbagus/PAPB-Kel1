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
    private val _text = MutableLiveData<String>().apply {
        value = "This is translator fragment"
    }


    val text:LiveData<String> = _text


    fun insert(history: HistoryModel) {
        viewModelScope.launch {
            database.insert(history)
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

