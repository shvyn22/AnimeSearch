package shvyn22.animesearch.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import shvyn22.animesearch.di.module.AppModule
import shvyn22.animesearch.presentation.MainActivity
import shvyn22.animesearch.presentation.bookmarks.BookmarksFragment
import shvyn22.animesearch.presentation.search.SearchFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface SingletonComponent {

	fun inject(mainActivity: MainActivity)

	fun inject(searchFragment: SearchFragment)

	fun inject(bookmarksFragment: BookmarksFragment)

	fun searchComponent(): SearchComponent.Factory

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application
		): SingletonComponent
	}
}