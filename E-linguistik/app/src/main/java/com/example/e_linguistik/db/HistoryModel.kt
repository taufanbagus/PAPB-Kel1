package com.example.e_linguistik.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryModel(
    @PrimaryKey(autoGenerate = true)
    var historyId: Long= 0L,

    @ColumnInfo(name = "origin_word")
    val originWord: String,

    @ColumnInfo(name = "result_word_translation")
    val resultWordTranslation: String,

    @ColumnInfo(name = "type_translation")
    val typeTranslation: String
)
