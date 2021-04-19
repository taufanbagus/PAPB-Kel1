package com.example.e_linguistik.ui.history

import android.app.Application
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.db.HistoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HistoryApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { HistoryDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { HistoryRepository(database.historyDatabaseDao()) }
}