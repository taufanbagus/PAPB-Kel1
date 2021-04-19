package com.example.e_linguistik.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.e_linguistik.HistoryAdapter
import com.example.e_linguistik.R
import com.example.e_linguistik.ui.translator.TranslatorViewModel

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    //private lateinit var historyViewModel: HistoryViewModel
    private val historyViewModel: HistoryViewModel by viewModels {
        val application = HistoryApplication()
        HistoryViewModelFactory(application.repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.history_fragment, container, false)
        /*
        val textView: TextView = root.findViewById(R.id.text_history)
        historyViewModel.text.observe(viewLifecycleOwner, Observer{
            textView.text = it
        })*/

        val rvHistory: RecyclerView = root.findViewById(R.id.rv_history)

        val adapter = HistoryAdapter()
        //rvHistory.adapter = adapter
        //rvHistory.layoutManager = LinearLayoutManager(MainActivity)
        rvHistory.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
        }

        /*historyViewModel.allHistory.observe(owner = viewLifecycleOwner) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }*/

        historyViewModel.allHistory.observe(viewLifecycleOwner){ history ->
            history.let { adapter.submitList(it) }
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}