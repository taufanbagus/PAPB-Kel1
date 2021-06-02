package com.example.e_linguistik.ui.history

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.example.e_linguistik.DI.Dependencies
import com.example.e_linguistik.MainActivity
import com.example.e_linguistik.R
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.ui.translator.TranslatorViewModel
import com.example.e_linguistik.ui.translator.TranslatorViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val btnDel: FloatingActionButton = root.findViewById(R.id.fab_del)

        val application = requireNotNull(this.activity).application

        val dataSource = Dependencies().DatabaseKoin.historyDatabaseDao
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
            showDialog(historyViewModel)
        }

        return root
    }

    private fun showDialog(historyViewModel: HistoryViewModel){
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog


        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(context)

        // Set a title for alert dialog
        builder.setTitle("Verifikasi")

        // Set a message for alert dialog
        builder.setMessage("Apakah anda yakin untuk menghapus semua history")


        // On click listener for dialog buttons
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> historyViewModel.delete()
            }
        }


        // Set the alert dialog positive/yes button
        builder.setPositiveButton("YES",dialogClickListener)

        // Set the alert dialog neutral/cancel button
        builder.setNeutralButton("CANCEL",dialogClickListener)

        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }

}