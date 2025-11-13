package com.example.tp1

import android.content.Context
import androidx.room.Room

object DB {
    private var db: AppDatabase? = null

    fun get(context: Context): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "form.db"
            ).build()
        }
        return db!!
    }
}
