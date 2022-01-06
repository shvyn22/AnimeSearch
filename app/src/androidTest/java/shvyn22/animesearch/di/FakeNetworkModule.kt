package shvyn22.animesearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import shvyn22.animesearch.api.FakeApiInterface
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object FakeNetworkModule {

    @Singleton
    @Provides
    fun provideApiInterface(): FakeApiInterface = FakeApiInterface()
}