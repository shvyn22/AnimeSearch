package shvyn22.animesearch.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Subcomponent
import shvyn22.animesearch.di.module.ActivityModule
import shvyn22.animesearch.presentation.util.MainNavHostFragment
import javax.inject.Scope

@SearchScope
@Subcomponent(modules = [ActivityModule::class])
interface SearchComponent {

	fun inject(mainNavHostFragment: MainNavHostFragment)

	@Subcomponent.Factory
	interface Factory {
		fun create(
			@BindsInstance activity: Context
		): SearchComponent
	}
}

@Scope
annotation class SearchScope