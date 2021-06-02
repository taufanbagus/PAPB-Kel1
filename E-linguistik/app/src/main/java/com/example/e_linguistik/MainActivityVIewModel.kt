package com.example.e_linguistik

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_linguistik.data.WordGroup
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivityVIewModel: ViewModel() {
    fun readFileTxt(activity: Activity){
        viewModelScope.launch {
            val reader = BufferedReader(InputStreamReader(activity.getAssets().open("word.txt")))
            val inputString = reader.use { it.readText() }

            WordGroup.listWordGroupData = inputString.split("\n")
        }
    }
}