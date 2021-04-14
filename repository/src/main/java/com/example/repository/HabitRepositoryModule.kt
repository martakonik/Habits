package com.example.repository

import com.example.domain.HabitsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class HabitRepositoryModule {

    @Binds
    abstract fun bindHabitRepository(impl: HabitRepositoryImp): HabitsRepository
}