package com.example.e_linguistik.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

abstract  class HistoryDatabase:RoomDatabase(){

    abstract val historyDatabaseDao: HistoryDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "translator_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return  instance
            }
        }
    }
}