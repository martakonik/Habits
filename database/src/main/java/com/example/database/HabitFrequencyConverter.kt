package com.example.database

import androidx.room.TypeConverter
import com.example.domain.data.HabitFrequency

class HabitFrequencyConverter {

    @TypeConverter
    fun toHabitFrequency(value: Int) = enumValues<HabitFrequency>()[value]

    @TypeConverter
    fun fromHabitFrequency(value: HabitFrequency) = value.ordinal
}