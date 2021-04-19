package com.example.e_linguistik.ui.translator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.e_linguistik.HistoryAdapter
import com.example.e_linguistik.MainActivity
import com.example.e_linguistik.R
import com.example.e_linguistik.db.HistoryModel
import com.example.e_linguistik.ui.history.HistoryApplication
import com.example.e_linguistik.ui.history.HistoryViewModel
import com.example.e_linguistik.ui.history.HistoryViewModelFactory

class TranslatorFragment : Fragment() {

    companion object {
        fun newInstance() = TranslatorFragment()
    }

    //private lateinit var translatorViewModel: TranslatorViewModel
    private val translatorViewModel: TranslatorViewModel by viewModels {
        val application = HistoryApplication()
        HistoryViewModelFactory(application.repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //translatorViewModel = ViewModelProvider(this).get(TranslatorViewModel::class.java)
        val root = inflater.inflate(R.layout.translator_fragment, container, false)
        val btnTranslate: Button = root.findViewById(R.id.btnTranslation)
        btnTranslate.setOnClickListener {
            val history = HistoryModel(0,"a","b","translate")
            translatorViewModel.insert(history)
        }

        /*
        val textView: TextView = root.findViewById(R.id.text_translator)
        translatorViewModel.text.observe(viewLifecycleOwner, Observer{
            textView.text = it
        })*/
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //translatorViewModel = ViewModelProvider(this).get(TranslatorViewModel::class.java)
        // TODO: Use the ViewModel
    }


}