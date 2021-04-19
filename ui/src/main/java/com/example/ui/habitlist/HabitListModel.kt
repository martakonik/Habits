package com.example.ui.habitlist

import com.example.domain.GetUserHabits
import com.example.domain.data.HabitWithStatePerDay
import com.example.domain.data.StatePerDay
import com.example.ui.util.MINUS_DAY_YESTERDAY
import com.example.ui.util.MINUS_TWO_DAYS_BEFORE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.LocalDate
import javax.inject.Inject

class HabitListModel @Inject constructor(
    private val getUserHabits: GetUserHabits
) {
    fun getUserHabits(): Flow<List<HabitItemWithRecentStates>> {
       return getUserHabits.get().map { list ->
            list.map { habitWithStatePerDay ->
                val now = LocalDate.now()
                val today: StatePerDay? = findStatePerDay(habitWithStatePerDay, now)
                val yesterday =
                    findStatePerDay(habitWithStatePerDay, now.minusDays(MINUS_DAY_YESTERDAY))
                val twoDaysBefore =
                    findStatePerDay(habitWithStatePerDay, now.minusDays(MINUS_TWO_DAYS_BEFORE))

                createHabitItemWithRecentStates(
                    habitWithStatePerDay,
                    today,
                    now,
                    yesterday,
                    twoDaysBefore
                )
            }
        }
    }

    private fun findStatePerDay(
        habitWithStatePerDay: HabitWithStatePerDay,
        now: LocalDate?
    ) = habitWithStatePerDay.statePerDayList.firstOrNull {
        it.date == now
    }

    private fun createHabitItemWithRecentStates(
        habitWithStatePerDay: HabitWithStatePerDay,
        today: StatePerDay?,
        now: LocalDate,
        yesterday: StatePerDay?,
        twoDaysBefore: StatePerDay?
    ) = HabitItemWithRecentStates(
        habitWithStatePerDay.habitItem,
        today ?: StatePerDay(
            habitId = habitWithStatePerDay.habitItem.id ?: 0,
            date = now,
            executed = false
        ),
        yesterday ?: StatePerDay(
            habitId = habitWithStatePerDay.habitItem.id ?: 0,
            date = now.minusDays(MINUS_DAY_YESTERDAY),
            executed = false
        ),
        twoDaysBefore ?: StatePerDay(
            habitId = habitWithStatePerDay.habitItem.id ?: 0,
            date = now.minusDays(MINUS_TWO_DAYS_BEFORE),
            executed = false
        )
    )
}