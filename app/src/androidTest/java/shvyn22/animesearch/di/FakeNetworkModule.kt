package shvyn22.animesearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import shvyn22.animesearch.data.remote.api.ApiService
import shvyn22.animesearch.data.remote.api.FakeApiService
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object FakeNetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = FakeApiService()
}