package shvyn22.animesearch.data.preferences

import io.reactivex.rxjava3.core.Observable

interface PreferencesManager {

    val isDarkTheme: Observable<Boolean>

    fun editThemePreferences(newThemeValue: Boolean)
}