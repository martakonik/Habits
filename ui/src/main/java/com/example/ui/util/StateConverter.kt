package com.example.ui.util

import com.example.domain.data.StatePerDay

object StateConverter {
    @JvmStatic
    fun bindToView(
        oldValue: StatePerDay
    ): Boolean {
        return oldValue.executed
    }
}