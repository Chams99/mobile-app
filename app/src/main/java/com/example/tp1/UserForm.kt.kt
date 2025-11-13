package com.example.tp1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class `UserForm`(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val gender: String,
    val country: String,
    val agreed: Boolean
)