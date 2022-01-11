package shvyn22.animesearch.presentation.search

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Rule
import shvyn22.animesearch.api.ApiInterface
import shvyn22.animesearch.api.FakeApiInterface
import shvyn22.animesearch.di.tearDownPreferencesDependencies
import shvyn22.animesearch.presentation.MainActivity
import javax.inject.Inject

@HiltAndroidTest
class SearchScreenTest {

	@get:Rule(order = 0)
	val hiltRule = HiltAndroidRule(this)

	@get:Rule(order = 1)
	val composeRule = createAndroidComposeRule<MainActivity>()

	@Inject
	lateinit var api: ApiInterface
	private val fakeApi: FakeApiInterface
		get() = api as FakeApiInterface

	@Inject
	lateinit var scope: CoroutineScope

	@Before
	fun init() {
		hiltRule.inject()
	}

	@After
	fun tearDown() {
		tearDownPreferencesDependencies(scope)
	}

	// TODO: create tests
}