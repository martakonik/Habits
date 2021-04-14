package com.example.repository

import com.example.database.HabitItemDao
import com.example.domain.HabitItem
import com.example.domain.HabitsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitRepositoryImp @Inject constructor(
    private val habitItemDao: HabitItemDao
) : HabitsRepository {

    override suspend fun saveHabit(item: HabitItem) {
        habitItemDao.insert(item)
    }

    override fun observeHabitsList(): Flow<List<HabitItem>> {
        return habitItemDao.getHabitList()
    }
}