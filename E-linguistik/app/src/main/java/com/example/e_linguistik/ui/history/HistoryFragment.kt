package com.example.e_linguistik.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.e_linguistik.HistoryAdapter
import com.example.e_linguistik.R
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.ui.translator.TranslatorViewModel
import com.example.e_linguistik.ui.translator.TranslatorViewModelFactory

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.history_fragment, container, false)

        val btnDel: Button = root.findViewById(R.id.btn_delete)

        val application = requireNotNull(this.activity).application

        val dataSource = HistoryDatabase.getInstance(application).historyDatabaseDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)
        historyViewModel = ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)


        val rvHistory: RecyclerView = root.findViewById(R.id.rv_history)

        val adapter = HistoryAdapter()
        rvHistory.adapter = adapter

        historyViewModel.hist.observe(viewLifecycleOwner) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        btnDel.setOnClickListener{
            historyViewModel.delete()
        }

        return root
    }

}