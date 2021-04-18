package com.example.ui

class OneTimeEvent<T>(private var value: T?) {

    fun consumeIfExists(): T? {
        val currentValue = value
        value = null
        return currentValue
    }

    companion object {
        fun <T> empty(): OneTimeEvent<T> = OneTimeEvent<T>(null)
    }
}