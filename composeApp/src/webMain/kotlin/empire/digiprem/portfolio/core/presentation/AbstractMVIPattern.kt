package empire.digiprem.portfolio.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

abstract class AbstractMVIPatternViewModel<state,action>(initialState: state): ViewModel() {

    private var hasLoadedInitialData = false


    protected val updateState= MutableStateFlow(initialState)
    val state = updateState.onStart {
        if (!hasLoadedInitialData) {
            onStart()
            hasLoadedInitialData = true
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = initialState
    )

    open fun onStart(){

    }
   abstract fun onAction(action: action)
}