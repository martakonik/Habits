package com.example.ui.habitlist

import android.widget.CompoundButton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UpdateHabitExecution
import com.example.domain.data.HabitItem
import com.example.domain.data.StatePerDay
import com.example.ui.BaseViewModel
import com.example.ui.R
import com.example.ui.util.MINUS_DAY_YESTERDAY
import com.example.ui.util.MINUS_TWO_DAYS_BEFORE
import com.example.ui.util.NavigationType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

interface Navigate {
    fun navigateWithId(habitId: Long)
}

@HiltViewModel
class HabitListItemViewModel @Inject constructor(
    private val update: UpdateHabitExecution
) : ViewModel() {

    fun navigateToHabitDetails(habitId: Long) {
        //poke do glownego VM z idhabitu by robilo event nav
//        navigate.navigateWithId(habitId)
//        navigateTo(
//            NavigationType.NavigateToDirection(
//                HabitsListFragmentDirections.actionHabitListFragmentToHabitDetailsFragment(habitId)
//            )
//        )
    }

    fun onCheckBoxChecked(
        view: CompoundButton,
        isChecked: Boolean,
        habitWithState: HabitItemWithRecentStates
    ) {
        val statePerDay = createStatePerDayBaseOnCheckboxId(view.id, habitWithState, isChecked)
        viewModelScope.launch(Dispatchers.Default) {
            if (alreadyInDatabase(statePerDay)) {
                update.update(statePerDay)
            } else {
                update.insert(statePerDay)
            }
        }
    }

    private fun alreadyInDatabase(statePerDay: StatePerDay) =
        statePerDay.id != null && statePerDay.id != 0.toLong()

    private fun createStatePerDayBaseOnCheckboxId(
        viewId: Int,
        habitWithState: HabitItemWithRecentStates,
        isChecked: Boolean
    ): StatePerDay {
        return when (viewId) {
            R.id.checkbox_day_before -> StatePerDay(
                habitId = habitWithState.habitItem.id,
                id = habitWithState.dayBeforeYesterday.id,
                date = LocalDate.now().minusDays(MINUS_TWO_DAYS_BEFORE),
                executed = isChecked
            )
            R.id.checkbox_yesterday -> StatePerDay(
                habitId = habitWithState.habitItem.id,
                id = habitWithState.yesterday.id,
                date = LocalDate.now().minusDays(MINUS_DAY_YESTERDAY),
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
    }
}

class HabitItemWithRecentStates(
    val habitItem: HabitItem,
    val today: StatePerDay,
    val yesterday: StatePerDay,
    val dayBeforeYesterday: StatePerDay
)
