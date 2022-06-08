package shvyn22.animesearch.presentation.util

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import shvyn22.animesearch.util.singletonComponent
import javax.inject.Inject

class MainNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context
            .singletonComponent
            .searchComponent().create(context)
            .inject(this)

        childFragmentManager.fragmentFactory = fragmentFactory
    }
}