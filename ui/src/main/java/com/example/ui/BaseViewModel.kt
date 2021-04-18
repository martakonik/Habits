package com.example.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {
    private val mNavigationStateFlow: MutableStateFlow<OneTimeEvent<NavigationType>> =
        MutableStateFlow(
            OneTimeEvent.empty()
        )

    val navigationFlow: Flow<OneTimeEvent<NavigationType>>
        get() = mNavigationStateFlow

    fun navigateTo(navigationType: NavigationType) {
        mNavigationStateFlow.value = OneTimeEvent(
            navigationType
        )
    }
}