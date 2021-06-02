package com.example.e_linguistik.ui.history

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.Transformations.map
import com.example.e_linguistik.db.HistoryDatabaseDao
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.ui.translator.TranslatorViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(
        val database:HistoryDatabaseDao,
        application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    lateinit var hist: LiveData<List<HistoryModel>>

    init {
        getHist()
    }
    fun getHist(){
        viewModelScope.launch {
            hist = database.getAllHistory()
        }
    }

    fun delete(){
        viewModelScope.launch {
            database.deleteAll()
        }
    }

}

class HistoryViewModelFactory(
        private val dataSource: HistoryDatabaseDao,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}