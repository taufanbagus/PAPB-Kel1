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

    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply {
        value = "This is KBBI fragment"
    }

    val text: LiveData<String> = _text

    fun insert(history: HistoryModel) {
        viewModelScope.launch {
            database.insert(history)
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