package com.example.ui.addhabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.HabitItem
import com.example.domain.data.NewHabit
import com.example.ui.NavigationType
import com.example.ui.OneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHabitViewModel @Inject constructor(
    private val model: AddHabitModel
) : ViewModel() {

    private val mStateFlow: MutableStateFlow<OneTimeEvent<NavigationType>> = MutableStateFlow(
        OneTimeEvent.empty()
    )

    val navigationFlow: Flow<OneTimeEvent<NavigationType>>
        get() = mStateFlow

    fun saveHabit(newHabit: NewHabit) = viewModelScope.launch {
        newHabit.name?.let { name ->
            model.saveHabit(HabitItem(frequency = newHabit.habitFrequency, name = name))
        }

        mStateFlow.value = OneTimeEvent(
            NavigationType.PopBackStack
        )
    }
}