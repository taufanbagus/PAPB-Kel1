package com.example.e_linguistik.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDatabaseDao {

    @Insert
    suspend fun insert(history: HistoryModel)

    @Query("SELECT * FROM history_table ORDER BY historyId")
    fun getAllHistory():Flow<List<HistoryModel>>

    @Query("DELETE FROM history_table")
    suspend fun deleteAll()

}