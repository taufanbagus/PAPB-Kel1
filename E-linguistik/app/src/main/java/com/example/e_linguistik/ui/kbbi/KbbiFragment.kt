package com.example.e_linguistik.ui.kbbi

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.e_linguistik.R
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.ui.history.HistoryViewModelFactory
import com.example.e_linguistik.ui.translator.TranslatorViewModel
import com.example.e_linguistik.ui.translator.TranslatorViewModelFactory

class KbbiFragment : Fragment() {

    companion object {
        fun newInstance() = KbbiFragment()
    }

    private lateinit var kbbiViewModel: KbbiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.kbbi_fragment, container, false)

        val btnTranslate: Button = root.findViewById(R.id.btnTranslateKBBI)
        val edtTransKbbi: EditText = root.findViewById(R.id.teksInput)

        val application = requireNotNull(this.activity).application

        val dataSource = HistoryDatabase.getInstance(application).historyDatabaseDao
        val viewModelFactory = KBBIViewModelFactory(dataSource, application)
        kbbiViewModel = ViewModelProvider(this, viewModelFactory).get(KbbiViewModel::class.java)

        btnTranslate.setOnClickListener {
            val value = edtTransKbbi.text.toString()
            val history = HistoryModel(originWord = value, resultWordTranslation = value, typeTranslation = "KBBI")
            kbbiViewModel.insert(history)
        }
        /*
        val textView: TextView = root.findViewById(R.id.text_kbbi)
        kbbiViewModel.text.observe(viewLifecycleOwner, Observer{
            textView.text = it
        })*/
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //kbbiViewModel = ViewModelProvider(this).get(KbbiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}