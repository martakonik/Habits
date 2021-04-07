package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.domain.HabitItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val mStateFlow: MutableStateFlow<List<HabitItem>> = MutableStateFlow(
        listOf(
            HabitItem("wake up at 7"),
            HabitItem("10k steps")
        )
    )

    val habitListFlow: StateFlow<List<HabitItem>>
        get() = mStateFlow


}