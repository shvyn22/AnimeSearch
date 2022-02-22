package shvyn22.animesearch.di.module

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import shvyn22.animesearch.data.preferences.PreferencesManager
import shvyn22.animesearch.data.preferences.PreferencesManagerImpl
import shvyn22.animesearch.util.DATASTORE_FILENAME
import javax.inject.Singleton

@Module
object PreferencesModule {

    @Singleton
    @Provides
    fun provideDataStore(app: Application): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                app.preferencesDataStoreFile(DATASTORE_FILENAME)
            }
        )

    @Singleton
    @Provides
    fun providePreferencesManager(
        dataStore: DataStore<Preferences>
    ): PreferencesManager = PreferencesManagerImpl(dataStore)
}