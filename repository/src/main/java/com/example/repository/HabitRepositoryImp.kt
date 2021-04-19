package com.example.repository

import com.example.database.HabitItemDao
import com.example.domain.HabitsRepository
import com.example.domain.data.HabitItem
import com.example.domain.data.HabitWithStatePerDay
import com.example.domain.data.StatePerDay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitRepositoryImp @Inject constructor(
    private val habitItemDao: HabitItemDao
) : HabitsRepository {

    override suspend fun saveHabit(item: HabitItem) {
        habitItemDao.insert(item)
    }

    override suspend fun updateHabitExecution(item: StatePerDay) {
        habitItemDao.update(item)
    }

    override suspend fun insertHabitExecution(item: StatePerDay) {
        habitItemDao.insert(item)
    }

    override fun observeHabitsList(): Flow<List<HabitWithStatePerDay>> {
        return habitItemDao.getHabitWithStatePerDay()
    }

    override fun getHabit(habitId: Long): Flow<HabitWithStatePerDay> {
        return habitItemDao.getHabitById(habitId)
    }
}