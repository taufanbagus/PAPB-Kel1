package com.example.e_linguistik.ui.kbbi

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.e_linguistik.R
import com.example.e_linguistik.ui.translator.TranslatorViewModel

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
        kbbiViewModel = ViewModelProvider(this).get(KbbiViewModel::class.java)
        val root = inflater.inflate(R.layout.kbbi_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_kbbi)
        kbbiViewModel.text.observe(viewLifecycleOwner, Observer{
            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        kbbiViewModel = ViewModelProvider(this).get(KbbiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}