package com.example.e_linguistik.ui.translator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.e_linguistik.HistoryAdapter
import com.example.e_linguistik.MainActivity
import com.example.e_linguistik.R
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.ui.history.HistoryViewModel


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

        val application = requireNotNull(this.activity).application

        val dataSource = HistoryDatabase.getInstance(application).historyDatabaseDao
        val viewModelFactory = TranslatorViewModelFactory(dataSource, application)
        translatorViewModel = ViewModelProvider(this, viewModelFactory).get(TranslatorViewModel::class.java)

        btnTranslate.setOnClickListener {
            val input = edtTranslator.text.toString()
            val history = HistoryModel(originWord = input,resultWordTranslation = input,typeTranslation = "translate")
            translatorViewModel.insert(history)
        }

        /*
        val textView: TextView = root.findViewById(R.id.text_translator)
        translatorViewModel.text.observe(viewLifecycleOwner, Observer{
            textView.text = it
        })*/
        return root
    }


}