package shvyn22.animesearch.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.animesearch.data.local.AppDatabase
import shvyn22.animesearch.data.local.dao.FavoriteDao
import shvyn22.animesearch.util.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase =
        Room
            .databaseBuilder(
                app,
                AppDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideFavoriteDao(db: AppDatabase): FavoriteDao =
        db.favoriteDao()
}