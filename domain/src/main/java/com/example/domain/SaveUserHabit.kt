package com.example.domain

class SaveUserHabit(
    private val repository: HabitsRepository
) {
    suspend fun save(item: HabitItem) {
        repository.saveHabit(item)
    }
}
