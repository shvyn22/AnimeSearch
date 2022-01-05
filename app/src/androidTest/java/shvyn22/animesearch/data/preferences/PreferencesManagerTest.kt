package shvyn22.animesearch.data.preferences

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.di.tearDownPreferencesDependencies
import javax.inject.Inject

@HiltAndroidTest
class PreferencesManagerTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        tearDownPreferencesDependencies(scope)
    }

    @Test
    fun editInitialMode_ReturnsModeNightYes() = runBlocking {

        preferencesManager.editNightMode(true)

        val nightMode = preferencesManager.nightMode.first()

        assertThat(
            nightMode,
            `is`(true)
        )
    }

    @Test
    fun toggleModes_ReturnsValidMode() = runBlocking {
        val initialMode = preferencesManager.nightMode.first()

        assertThat(
            initialMode,
            `is`(false)
        )

        preferencesManager.editNightMode(true)

        val afterChangeMode = preferencesManager.nightMode.first()

        assertThat(
            afterChangeMode,
            `is`(true)
        )
    }
}