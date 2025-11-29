package com.example.tp1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserFormDao {
    @Insert
    suspend fun insert(form: UserForm)

    @Query("SELECT * FROM userform")
    suspend fun getAll(): List<UserForm>
}
