package shvyn22.animesearch.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import shvyn22.animesearch.data.local.model.Bookmark

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM Bookmark")
    fun getBookmarks(): Flow<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(item: Bookmark)

    @Query("DELETE FROM Bookmark WHERE id = :id")
    suspend fun deleteBookmark(id: Int)

    @Query("DELETE FROM Bookmark")
    suspend fun deleteBookmarks()
}