package com.example.e_linguistik.ui.history

import androidx.lifecycle.*
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.db.HistoryRepository
import com.example.e_linguistik.ui.translator.TranslatorViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply {
        value = "This is History fragment"
    }

    val text: LiveData<String> = _text

    val allHistory: LiveData<List<HistoryModel>> = repository.all_history.asLiveData()

}

class HistoryViewModelFactory(private val repository: HistoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TranslatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}