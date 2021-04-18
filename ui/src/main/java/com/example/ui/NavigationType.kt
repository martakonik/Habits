package com.example.ui

import androidx.navigation.NavDirections

sealed class NavigationType {
    class NavigateToDirection(val direction: NavDirections) : NavigationType()
    object PopBackStack : NavigationType()
}
