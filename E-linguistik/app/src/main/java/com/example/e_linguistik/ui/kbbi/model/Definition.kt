package com.example.e_linguistik.ui.kbbi.model

data class Definition(
    val def_num: String,
    val def_text: String,
    val def_uid: String,
    val discipline: Any,
    val lex_class: Any,
    val lex_class_ref: Any,
    val phrase: String,
    val sample: Any,
    val see: Any,
    val updated: Any,
    val updater: Any,
    val wikipedia_updated: Any
)