package shvyn22.animesearch.di.module

import dagger.Module
import shvyn22.animesearch.di.component.SearchComponent

@Module(
    includes = [
        DatabaseModule::class,
        NetworkModule::class,
        PreferencesModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ],
    subcomponents = [SearchComponent::class]
)
object AppModule