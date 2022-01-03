package shvyn22.animesearch.ui.search

import androidx.activity.result.ActivityResultRegistry
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.animesearch.R

@AndroidEntryPoint
class SearchFragment(
    private val registry: ActivityResultRegistry
) : Fragment(R.layout.fragment_search) {
}