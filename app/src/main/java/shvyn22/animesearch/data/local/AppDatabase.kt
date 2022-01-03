package shvyn22.animesearch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import shvyn22.animesearch.data.local.dao.BookmarkDao
import shvyn22.animesearch.data.local.model.AnimeModel

@Database(
    entities = [AnimeModel::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun bookmarkDao() : BookmarkDao
}