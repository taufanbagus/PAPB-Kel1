package com.example.e_linguistik.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_linguistik.MainActivity
import com.example.e_linguistik.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tvWordtoday: TextView
    private lateinit var tvTransWordToday: TextView
    private lateinit var tvKbbiToday: TextView
    private lateinit var tvTransKbbiToday: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        tvWordtoday = root.findViewById(R.id.tv_wordtoday)
        tvTransWordToday = root.findViewById(R.id.tv_transwordtoday)
        tvKbbiToday = root.findViewById(R.id.tv_kbbitoday)
        tvTransKbbiToday = root.findViewById(R.id.tv_transkbbiwordtoday)

        //homeViewModel.readDataToDay(MainActivity())
        return root
    }

    override fun onResume() {
        super.onResume()
        val vocab = arrayOf("Father","Mother","Children","Uncle","Aunt")
        val transVocab = arrayOf("Ayah","Ibu","Anak-anak","Paman","Bibi")
        val kbbi = arrayOf("Gunting","Pena","Pensil","Penghapus","Penggaris")
        val transKbbi = arrayOf("perkakas untuk memotong kain (rambut dan sebagainya)",
                        "alat untuk menulis dengan tinta, dibuat dari baja dan sebagainya yang runcing dan belah",
                        "alat tulis berupa kayu kecil bulat berisi arang keras",
                        "alat untuk menghapus tulisan pada papan tulis dan sebagainya",
                        "alat untuk membuat garis")
        val ranNumb = (0..4).random()

        //homeViewModel.translateWord(transVocab[1])
        //homeViewModel.translateUsingKBBI(kbbi[1])

        homeViewModel.getDataToDay()

        homeViewModel.dataResultTrans.observe(viewLifecycleOwner, Observer {
            tvTransWordToday.text = it
        })
        homeViewModel.dataResultKbbi.observe(viewLifecycleOwner, Observer {
            tvTransKbbiToday.text = it
        })


        tvWordtoday.text = transVocab[1]
        //tvTransWordToday.text = transVocab[ranNumb]
        tvKbbiToday.text = kbbi[1]
        //tvTransKbbiToday.text = transKbbi[ranNumb]
    }
}