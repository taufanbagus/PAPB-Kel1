package com.example.e_linguistik

import com.example.e_linguistik.R
import java.util.ArrayList

object BioData {
    private val nameMember = arrayOf("Luqman Rofif Muntashie",
                            "Taufan Bagus Grahita",
                            "Dede Trimulya")

    private val nimMember = arrayOf("18/425314/TK/47009",
                            "",
                            "")

    private val ttlMember = arrayOf("6 Agustus 2000",
                            "",
                            "")

    private val asalMember = arrayOf("Solo",
                            "Purwokerto",
                            "Padang")

    /*
    Struktur sosmed
    instagram, facebook, linkedin, github
     */

    private val sosmedMember = arrayOf(
            arrayOf("luqmanrmrofif", "luqman", "Luqman Rofif Muntashir", "luqmanrmrofif"),
            arrayOf("taufanbg", "Taufan Bagus", "Taufan Bagus Grahita", "Taufan Bagus"),
            arrayOf("dedetrimulya", "Dede Trimulya", "Dede Trimulya", "dedeTr")
    )

    private val imageMember = intArrayOf(R.drawable.luqman,
                            R.drawable.topan,
                            R.drawable.dede)

    val listData: ArrayList<Biografi>
        get() {
            val list = arrayListOf<Biografi>()
            for (position in nameMember.indices) {
                val biografi = Biografi()
                biografi.name = nameMember[position]
                biografi.nim = nimMember[position]
                biografi.ttl = ttlMember[position]
                biografi.asal = asalMember[position]
                biografi.sosmed = sosmedMember[position]
                biografi.foto = imageMember[position]

                list.add(biografi)
            }
            return list
        }
}