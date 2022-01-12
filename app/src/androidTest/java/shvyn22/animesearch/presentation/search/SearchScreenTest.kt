package shvyn22.animesearch.presentation.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.animesearch.R
import shvyn22.animesearch.api.ApiInterface
import shvyn22.animesearch.api.FakeApiInterface
import shvyn22.animesearch.di.tearDownPreferencesDependencies
import shvyn22.animesearch.presentation.MainActivity
import shvyn22.animesearch.util.ERROR_FETCHING
import shvyn22.animesearch.util.animeDTO1
import shvyn22.animesearch.util.animeDTO2
import shvyn22.animesearch.util.animeDTOs
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

	@Test
	fun remoteIsAvailable_populateApiWith2Items_2ItemsAreInView() {
		fakeApi.initResponse(animeDTOs)

		composeRule.apply {
			onNodeWithText(activity.getString(R.string.text_upload_from_file))
				.performClick()

			onNodeWithText(activity.getString(R.string.text_search))
				.performClick()

			onNodeWithText(
				activity.getString(
					R.string.text_title, animeDTO1.anilistInfo.title.toString()
				)
			)
				.assertIsDisplayed()

			onNodeWithText(
				activity.getString(
					R.string.text_title, animeDTO2.anilistInfo.title.toString()
				)
			)
				.assertIsDisplayed()
		}
	}

	@Test
	fun remoteIsAvailable_populateApiWithNoItems_ErrorIsThrown() {
		composeRule.apply {
			onNodeWithText(activity.getString(R.string.text_upload_from_file))
				.performClick()

			onNodeWithText(activity.getString(R.string.text_search))
				.performClick()

			onNodeWithText(
				activity.getString(
					R.string.text_title, animeDTO1.anilistInfo.title.toString()
				)
			)
				.assertDoesNotExist()

			onNodeWithText(
				activity.getString(
					R.string.text_title, animeDTO2.anilistInfo.title.toString()
				)
			)
				.assertDoesNotExist()

			onNodeWithText(ERROR_FETCHING)
				.assertIsDisplayed()
		}
	}

	@Test
	fun remoteIsNotAvailable_ErrorIsThrown() {
		fakeApi.changeFailBehaviour(true)

		composeRule.apply {
			onNodeWithText(activity.getString(R.string.text_upload_from_file))
				.performClick()

			onNodeWithText(activity.getString(R.string.text_search))
				.performClick()

			onNodeWithText(
				activity.getString(
					R.string.text_title, animeDTO1.anilistInfo.title.toString()
				)
			)
				.assertDoesNotExist()

			onNodeWithText(
				activity.getString(
					R.string.text_title, animeDTO2.anilistInfo.title.toString()
				)
			)
				.assertDoesNotExist()

			onNodeWithText(ERROR_FETCHING)
				.assertIsDisplayed()
		}
	}
}