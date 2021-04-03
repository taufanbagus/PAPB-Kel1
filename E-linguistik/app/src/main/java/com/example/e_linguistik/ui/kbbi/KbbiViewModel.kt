package com.example.e_linguistik.ui.kbbi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KbbiViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply {
        value = "This is KBBI fragment"
    }

    val text: LiveData<String> = _text
}