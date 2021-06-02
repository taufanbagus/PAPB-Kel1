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

        //val dataSource = HistoryDatabase.getInstance(application).historyDatabaseDao
        val dataSource = Dependencies().DatabaseKoin.historyDatabaseDao
        val viewModelFactory = TranslatorViewModelFactory(dataSource, application)
        translatorViewModel = ViewModelProvider(this, viewModelFactory).get(TranslatorViewModel::class.java)
        val BASE_URL_TRANSLATOR = "https://amm-api-translate.herokuapp.com/"

//      url Documentation API https://github.com/azharimm/api-translate
//      url API https://amm-api-translate.herokuapp.com/translate?engine={engine}&text={text}&to={to}

        btnTranslate.setOnClickListener {

            val input = edtTranslator.text.toString()
            val retrofiBuilder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL_TRANSLATOR)
                    .build()
                    .create(TranslatorAPIInterface::class.java)

            val translatorData = retrofiBuilder.getData("google", input, "en",)

            var translatorResult: String = ""

            translatorData.enqueue(object : Callback<TranslatorData?> {
                override fun onFailure(call: Call<TranslatorData?>, t: Throwable) {
                    Log.e("translatorData", "onFailure: "+t.message)
                }

                override fun onResponse(call: Call<TranslatorData?>, response: Response<TranslatorData?>) {
                    val res = response.body()!!
                    translatedWord.text = res.data.result
                    translatorResult = res.data.result
                }
            })
            Log.e("translateData","hasil translate" + translatorResult)
            val history = HistoryModel(originWord = input,resultWordTranslation = translatorResult,typeTranslation = "Translate")
            translatorViewModel.insert(history)
        }
        return root
    }
}