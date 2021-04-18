package com.example.domain.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate


@Entity
data class StatePerDay(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val habitId: Long?,
    val date: LocalDate,
    val executed: Boolean
)