package com.example.e_linguistik.db

import androidx.annotation.WorkerThread

class HistoryRepository(private  val  historyDao: HistoryDatabaseDao) {
    val all_history = historyDao.getAllHistory()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(history: HistoryModel){
        historyDao.insert(history)
    }
}