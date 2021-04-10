package com.example.e_linguistik.model

data class Biografi(
    var name: String = "",
    var nim: String = "",
    var ttl: String = "",
    var asal: String = "",
    var sosmed: Array<String> = arrayOf(),
    var foto: Int = 0
)
