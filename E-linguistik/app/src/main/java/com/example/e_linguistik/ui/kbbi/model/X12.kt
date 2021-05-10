package com.example.e_linguistik.ui.kbbi.model

data class X12(
    val lex_class: String,
    val rel_type: String,
    val rel_type_name: String,
    val rel_uid: String,
    val related_phrase: String,
    val root_phrase: String,
    val updated: String,
    val updater: String
)