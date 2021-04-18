package com.example.database

import androidx.room.*
import com.example.domain.data.HabitItem
import com.example.domain.data.HabitWithStatePerDay
import com.example.domain.data.StatePerDay
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(habitItem: HabitItem)

    @Transaction
    @Query("SELECT * FROM habit_item")
    fun getHabitWithStatePerDay(): Flow<List<HabitWithStatePerDay>>

    @Delete
    suspend fun delete(habitItem: HabitItem)

    @Update(entity = StatePerDay::class)
    suspend fun update(obj: StatePerDay)

    @Insert
    suspend fun insert(obj: StatePerDay)
}