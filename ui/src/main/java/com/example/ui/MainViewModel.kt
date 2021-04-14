package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.domain.GetUserHabits
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserHabits: GetUserHabits
) : ViewModel() {

    fun getListFromDatabase() = getUserHabits.get()

}
