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

        val dataSource = HistoryDatabase.getInstance(application).historyDatabaseDao
        val viewModelFactory = KBBIViewModelFactory(dataSource, application)
        kbbiViewModel = ViewModelProvider(this, viewModelFactory).get(KbbiViewModel::class.java)

        val BASE_URL_KBBI = "https://kateglo.com/"

        btnTranslate.setOnClickListener {

            val value = edtTransKbbi.text.toString()
            val retrofiBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL_KBBI)
                .build()
                .create(KbbiAPIInterface::class.java)

            val kbbiData = retrofiBuilder.getData("json", value)
            var kbbiResult: String = ""

            kbbiData.enqueue(object : Callback<KbbiData?> {
                override fun onFailure(call: Call<KbbiData?>, t: Throwable) {
                    Log.e("kbbiData", "onFailure: "+t.message)
                }

                override fun onResponse(call: Call<KbbiData?>, response: Response<KbbiData?>) {
                    val res = response.body()!!
                    val kbbistringBuilder = StringBuilder()
                    val arrDef = res.kateglo.definition
                    for(kbbiDef in arrDef){
                        kbbistringBuilder.append(kbbiDef.def_text)
                        kbbistringBuilder.append(".\n")
                    }
                    //Log.e("Respone Kbbi", "onResponse: "+kbbistringBuilder )
                    artiKbbi.text = kbbistringBuilder
                    kbbiResult = kbbistringBuilder.toString()
                }
            })


            val history = HistoryModel(originWord = value, resultWordTranslation = kbbiResult, typeTranslation = "KBBI")
            kbbiViewModel.insert(history)
        }


//        fun getKbbiData(){
//
//        }

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