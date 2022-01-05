package shvyn22.animesearch.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import shvyn22.animesearch.data.local.model.Bookmark

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM Bookmark")
    fun getItems(): Flow<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Bookmark)

    @Query("DELETE FROM Bookmark WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM Bookmark")
    suspend fun deleteAll()
}