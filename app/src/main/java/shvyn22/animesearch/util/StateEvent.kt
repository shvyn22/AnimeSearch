package shvyn22.animesearch.util

sealed class StateEvent {
    data class NavigateToAnilist(val id: Int) : StateEvent()
    data class ShowError(val error: ErrorType) : StateEvent()
}

