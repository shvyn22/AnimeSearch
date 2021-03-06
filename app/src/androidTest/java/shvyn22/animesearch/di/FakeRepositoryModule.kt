package shvyn22.animesearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.local.model.Bookmark
import shvyn22.animesearch.data.remote.api.ApiService
import shvyn22.animesearch.repository.FakeLocalRepository
import shvyn22.animesearch.repository.FakeRemoteRepository
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.repository.remote.RemoteRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object FakeRepositoryModule {

    @Singleton
    @Provides
    fun provideLocalRepository(
        dao: BookmarkDao,
    ): LocalRepository<Bookmark> = FakeLocalRepository(dao)

    @Singleton
    @Provides
    fun provideRemoteRepository(
        api: ApiService,
        dao: BookmarkDao,
    ): RemoteRepository<AnimeModel> = FakeRemoteRepository(api, dao)
}