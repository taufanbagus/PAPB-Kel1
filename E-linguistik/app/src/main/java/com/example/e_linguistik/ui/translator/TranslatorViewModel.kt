package com.example.e_linguistik.ui.translator

import androidx.lifecycle.*
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.db.HistoryDatabaseDao
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.db.HistoryRepository
import kotlinx.coroutines.launch

class TranslatorViewModel(private val repository: HistoryRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply {
        value = "This is translator fragment"
    }

    val text:LiveData<String> = _text

    fun insert(history: HistoryModel) = viewModelScope.launch {
        repository.insert(history)
    }


}

