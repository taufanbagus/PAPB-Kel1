package com.example.e_linguistik.ui.translator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.db.HistoryDatabaseDao
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.db.HistoryRepository

class TranslatorViewModel(private val repository: HistoryRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply {
        value = "This is translator fragment"
    }

    val text:LiveData<String> = _text

    val allHistory: LiveData<List<HistoryModel>> = repository.all_history
}

class HistoryViewModelFactory(private val repository: HistoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TranslatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TranslatorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}