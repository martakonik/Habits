package com.example.database

import androidx.room.*
import com.example.domain.HabitItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitItemDao {
    @Query("SELECT * FROM habit_item ORDER BY name ASC")
    fun getHabitList(): Flow<List<HabitItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(habitItem: HabitItem)


    @Delete
    suspend fun delete(habitItem: HabitItem)

}