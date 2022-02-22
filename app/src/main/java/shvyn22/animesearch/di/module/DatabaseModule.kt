package shvyn22.animesearch.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import shvyn22.animesearch.data.local.AppDatabase
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.util.DATABASE_NAME
import javax.inject.Singleton

@Module
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
    fun provideBookmarkDao(db: AppDatabase): BookmarkDao =
        db.bookmarkDao()
}