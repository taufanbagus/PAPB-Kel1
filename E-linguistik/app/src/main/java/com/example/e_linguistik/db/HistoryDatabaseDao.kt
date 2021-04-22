package com.example.e_linguistik.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDatabaseDao {

    @Insert
    suspend fun insert(history: HistoryModel)

    @Update
    fun update(historyData: HistoryModel)

    @Query("SELECT * FROM history_table ORDER BY historyId")
    fun getAllHistory():LiveData<List<HistoryModel>>

    @Query("DELETE FROM history_table")
    suspend fun deleteAll()

}