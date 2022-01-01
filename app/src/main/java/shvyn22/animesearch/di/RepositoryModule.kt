package shvyn22.animesearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.animesearch.api.ApiInterface
import shvyn22.animesearch.data.local.dao.FavoriteDao
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.data.remote.AnimeInfo
import shvyn22.animesearch.repository.local.LocalRepository
import shvyn22.animesearch.repository.local.LocalRepositoryImpl
import shvyn22.animesearch.repository.remote.RemoteRepository
import shvyn22.animesearch.repository.remote.RemoteRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLocalRepository(
        favoriteDao: FavoriteDao
    ): LocalRepository<AnimeModel> = LocalRepositoryImpl(favoriteDao)

    @Singleton
    @Provides
    fun provideRemoteRepository(
        api: ApiInterface
    ): RemoteRepository<AnimeInfo> = RemoteRepositoryImpl(api)
}