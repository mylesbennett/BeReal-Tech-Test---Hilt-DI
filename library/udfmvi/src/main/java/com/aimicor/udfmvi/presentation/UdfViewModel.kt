package com.aimicor.udfmvi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aimicor.udfmvi.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * References:
 *
 * https://developer.android.com/jetpack/compose/architecture
 * https://developer.android.com/jetpack/compose/side-effects
 * https://developer.android.com/topic/architecture/ui-layer#why-use-udf
 * https://proandroiddev.com/mvi-architecture-with-kotlin-flows-and-channels-d36820b2028d
 */
abstract class UdfViewModel<EV : Event, ST : UiState, EF : SideEffect> : ViewModel() {

    private val _uiState: MutableStateFlow<ST> by lazy { MutableStateFlow(startingUiState()) }
    val uiState = _uiState.asStateFlow()

    private val _sideEffect: Channel<EF> = Channel()
    val sideEffect by lazy { _sideEffect.receiveAsFlow() }

    abstract fun handleEvent(event: EV)

    protected abstract fun startingUiState(): ST

    protected fun setUiState(reduce: ST.() -> ST) {
        _uiState.value = uiState.value.reduce()
    }

    protected fun sendSideEffect(builder: () -> EF) {
        viewModelScope.launch {
            _sideEffect.send(builder())
        }
    }
}
