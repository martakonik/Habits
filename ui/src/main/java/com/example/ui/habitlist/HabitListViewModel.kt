package com.example.ui.habitlist

import android.widget.CompoundButton
import androidx.lifecycle.viewModelScope
import com.example.domain.GetUserHabits
import com.example.domain.UpdateHabitExecution
import com.example.domain.data.HabitItem
import com.example.domain.data.StatePerDay
import com.example.ui.BaseViewModel
import com.example.ui.NavigationType
import com.example.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class HabitListViewModel @Inject constructor(
    private val getUserHabits: GetUserHabits
) : BaseViewModel() {

    fun getListFromDatabaseAndState() = getUserHabits.get()
        .map {
            it.map {
                it.habitItem
                val now = LocalDate.now()
                val today: StatePerDay? = it.statePerDayList.firstOrNull { it.date == now }
                val yesterday = it.statePerDayList.firstOrNull { it.date == now.minusDays(1) }
                val twoDaysBefore = it.statePerDayList.firstOrNull { it.date == now.minusDays(2) }

                HabitListItemViewColkolwiek(
                    it.habitItem,
                    today ?: StatePerDay(
                        habitId = it.habitItem.id ?: 0,
                        date = now,
                        executed = false
                    ),
                    yesterday ?: StatePerDay(
                        habitId = it.habitItem.id ?: 0,
                        date = now.minusDays(1),
                        executed = false
                    ),
                    twoDaysBefore ?: StatePerDay(
                        habitId = it.habitItem.id ?: 0,
                        date = now.minusDays(2),
                        executed = false
                    )
                )
            }
        }

    fun addNewHabit() {
        navigateTo(
            NavigationType.NavigateToDirection(
                HabitsListFragmentDirections.actionHabitListFragmentToAddHabitFragment()
            )
        )
    }
}

@HiltViewModel
class HabitListItemViewModel @Inject constructor(
    private val update: UpdateHabitExecution
) : BaseViewModel() {

    fun checkBoxChecked(
        view: CompoundButton,
        isChecked: Boolean,
        habitWithState: HabitListItemViewColkolwiek
    ) {
        val statePerDayUpdate = when (view.id) {
            R.id.checkbox_day_before -> StatePerDay(
                habitId = habitWithState.habitItem.id,
                id = habitWithState.dayBeforeYesterday.id,
                date = LocalDate.now().minusDays(2),
                executed = isChecked
            )
            R.id.checkbox_yesterday -> StatePerDay(
                habitId = habitWithState.habitItem.id,
                id = habitWithState.yesterday.id,
                date = LocalDate.now().minusDays(1),
                executed = isChecked
            )
            R.id.checkbox_today -> StatePerDay(
                habitId = habitWithState.habitItem.id,
                id = habitWithState.today.id,
                date = LocalDate.now(),
                executed = isChecked
            )
            else -> TODO() //add error handling
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (statePerDayUpdate.id != null && statePerDayUpdate.id != 0.toLong()) {
                update.update(statePerDayUpdate)
            } else {
                update.insert(statePerDayUpdate)
            }
        }
    }
}

class HabitListItemViewColkolwiek(
    val habitItem: HabitItem,
    val today: StatePerDay,
    val yesterday: StatePerDay,
    val dayBeforeYesterday: StatePerDay
)
