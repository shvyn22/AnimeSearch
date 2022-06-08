package shvyn22.animesearch.presentation.util

import androidx.activity.result.ActivityResultRegistry
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import shvyn22.animesearch.presentation.bookmarks.BookmarksFragment
import shvyn22.animesearch.presentation.search.SearchFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val registry: ActivityResultRegistry
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            SearchFragment::class.java.name -> SearchFragment(registry)
            BookmarksFragment::class.java.name -> BookmarksFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}