package com.example.tp1

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserForm::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): UserFormDao
}
