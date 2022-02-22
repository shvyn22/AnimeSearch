package shvyn22.animesearch.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shvyn22.animesearch.di.util.ViewModelKey
import shvyn22.animesearch.presentation.MainViewModel
import shvyn22.animesearch.presentation.bookmarks.BookmarksViewModel
import shvyn22.animesearch.presentation.search.SearchViewModel

@Module
interface ViewModelModule {

	@Binds
	@[IntoMap ViewModelKey(MainViewModel::class)]
	fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

	@Binds
	@[IntoMap ViewModelKey(SearchViewModel::class)]
	fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

	@Binds
	@[IntoMap ViewModelKey(BookmarksViewModel::class)]
	fun bindBookmarksViewModel(bookmarksViewModel: BookmarksViewModel): ViewModel
}