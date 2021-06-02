package com.example.e_linguistik.DI

import android.content.Context
import androidx.room.Room
import com.example.e_linguistik.db.HistoryDatabase
import com.example.e_linguistik.ui.kbbi.KbbiAPIInterface
import com.example.e_linguistik.ui.kbbi.KbbiFragment
import com.example.e_linguistik.ui.translator.TranslatorAPIInterface
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val kbbiClient = module {
    val BASE_URL_KBBI = "https://kateglo.com/"

    val instance: KbbiAPIInterface by lazy{
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL_KBBI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitBuilder.create(KbbiAPIInterface::class.java)
    }

    single {
        instance
    }
}

val translatorClient = module {
    val BASE_URL_TRANSLATOR = "https://amm-api-translate.herokuapp.com/"

    val instance: TranslatorAPIInterface by lazy{
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL_TRANSLATOR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitBuilder.create(TranslatorAPIInterface::class.java)
    }

    single {
        instance
    }
}

val databaseApp = module {
    var INSTANCE: HistoryDatabase? = null

    fun getInstance(context: Context): HistoryDatabase {
        synchronized(this) {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDatabase::class.java,
                    "history_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

    single {
        getInstance(get())
    }
}


class Dependencies:KoinComponent {
    val kbbiKoin: KbbiAPIInterface by inject()
    val TranslatorKoin: TranslatorAPIInterface by inject()
    val DatabaseKoin: HistoryDatabase by inject()
}