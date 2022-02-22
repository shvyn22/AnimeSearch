package shvyn22.animesearch.data.preferences

import io.reactivex.rxjava3.core.Observable

interface PreferencesManager {

    val nightMode: Observable<Boolean>

    fun editNightMode(nightMode: Boolean)
}