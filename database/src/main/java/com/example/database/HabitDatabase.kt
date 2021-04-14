package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.HabitItem

@Database(entities = [HabitItem::class], version = 1)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitItemDao(): HabitItemDao
}