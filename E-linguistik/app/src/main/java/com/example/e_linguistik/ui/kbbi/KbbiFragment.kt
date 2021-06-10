package com.example.e_linguistik.ui.kbbi

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
import com.example.e_linguistik.ui.history.HistoryViewModelFactory
import com.example.e_linguistik.ui.translator.TranslatorViewModel
import com.example.e_linguistik.ui.translator.TranslatorViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.e_linguistik.ui.kbbi.model.KbbiData

class KbbiFragment : Fragment() {

    companion object {
        fun newInstance() = KbbiFragment()
    }

    private lateinit var kbbiViewModel: KbbiViewModel
    private val a = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.kbbi_fragment, container, false)

        val btnTranslate: Button = root.findViewById(R.id.btnTranslateKBBI)
        val edtTransKbbi: EditText = root.findViewById(R.id.teksInput)
        val artiKbbi: TextView = root.findViewById(R.id.Kbbiresult)

        val application = requireNotNull(this.activity).application

        val dataSource = Dependencies().DatabaseKoin.historyDatabaseDao
        val viewModelFactory = KBBIViewModelFactory(dataSource, application)
        kbbiViewModel = ViewModelProvider(this, viewModelFactory).get(KbbiViewModel::class.java)

        btnTranslate.setOnClickListener {

            val value = edtTransKbbi.text.toString()
            if (value.isEmpty()){
                edtTransKbbi.error = "Kata belum dimasukkan"
            } else {
                kbbiViewModel.translateUsingKBBI(value)
                kbbiViewModel.dataResultKbbi.observe(viewLifecycleOwner, Observer {
                    artiKbbi.text = it
                    val history = HistoryModel(originWord = value, resultWordTranslation = it, typeTranslation = "KBBI")
                    kbbiViewModel.insert(history)
                })
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}