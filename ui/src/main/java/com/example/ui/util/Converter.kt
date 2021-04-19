package com.example.ui.util

import androidx.databinding.InverseMethod
import com.example.domain.data.HabitFrequency
import com.example.ui.R

object Converter {

    @InverseMethod("bindFromView")
    @JvmStatic
    fun bindToView(
        oldValue: HabitFrequency
    ): Int {
        return when (oldValue) {
            HabitFrequency.EVERY_DAY -> R.id.radioButtonEveryDay
            HabitFrequency.WEEKDAYS -> R.id.radioButtonFiveTimesPerWeek
            HabitFrequency.ONCE_A_WEEK -> R.id.radioButtonOnceAWeek
        }
    }

    @JvmStatic
    fun bindFromView(
        oldValue: Int
    ): HabitFrequency {
        return when (oldValue) {
            R.id.radioButtonEveryDay -> HabitFrequency.EVERY_DAY
            R.id.radioButtonFiveTimesPerWeek -> HabitFrequency.WEEKDAYS
            R.id.radioButtonOnceAWeek -> HabitFrequency.ONCE_A_WEEK
            else -> TODO()// error handling
        }
    }
}