package com.example.e_linguistik.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDatabaseDao {

    @Insert
    suspend fun insert(history: HistoryModel)

    @Query("SELECT * FROM history_table ORDER BY historyId")
    fun getAllHistory():LiveData<List<HistoryModel>>

}