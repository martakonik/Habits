package com.example.domain.data

import androidx.room.Embedded
import androidx.room.Relation

data class HabitWithStatePerDay(
    @Embedded val habitItem: HabitItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId"
    )
    val statePerDayList: List<StatePerDay>
)