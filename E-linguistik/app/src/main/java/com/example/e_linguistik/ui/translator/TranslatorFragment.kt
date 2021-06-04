package com.example.e_linguistik.ui.translator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.e_linguistik.DI.Dependencies
import com.example.e_linguistik.R
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.ui.translator.model.Data
import com.example.e_linguistik.ui.translator.model.TranslatorData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TranslatorFragment : Fragment() {

    companion object {
        fun newInstance() = TranslatorFragment()
    }
    private lateinit var translatorViewModel: TranslatorViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.translator_fragment, container, false)
        val btnTranslate: Button = root.findViewById(R.id.btnTranslation)
        val edtTranslator: EditText = root.findViewById(R.id.translatorInput)
        val translatedWord: TextView =  root.findViewById(R.id.rvListTranslation)

        val application = requireNotNull(this.activity).application
        val dataSource = Dependencies().DatabaseKoin.historyDatabaseDao
        val viewModelFactory = TranslatorViewModelFactory(dataSource, application)
        translatorViewModel = ViewModelProvider(this, viewModelFactory).get(TranslatorViewModel::class.java)

        //url Documentation API https://github.com/azharimm/api-translate
        //url API https://amm-api-translate.herokuapp.com/translate?engine={engine}&text={text}&to={to}

        //fungsi yang berjalan ketika tombol "terjemahkan" diklik
        btnTranslate.setOnClickListener {

            val input = edtTranslator.text.toString()
            translatorViewModel.translateWord(input)
            translatorViewModel.dataResultTrans.observe(viewLifecycleOwner, Observer {
                translatedWord.text = it
                val history = HistoryModel(originWord = input,resultWordTranslation = it,typeTranslation = "Translate")
                translatorViewModel.insert(history)
            })

        }
        return root
    }
}