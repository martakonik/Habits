package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.data.HabitItem
import com.example.domain.data.StatePerDay

@Database(
    entities = [
        HabitItem::class,
        StatePerDay::class,
    ], version = 8
)
@TypeConverters(HabitFrequencyConverter::class, LocalTimeConverter::class)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitItemDao(): HabitItemDao
}