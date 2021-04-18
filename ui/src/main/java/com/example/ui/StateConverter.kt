package com.example.ui

import com.example.domain.data.StatePerDay

object StateConverter {
    @JvmStatic
    fun bindToView(
        oldValue: StatePerDay
    ): Boolean {
        return oldValue.executed
    }
}