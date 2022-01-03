package shvyn22.animesearch.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import shvyn22.animesearch.data.local.model.AnimeModel

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM Bookmark")
    suspend fun getItems(): Flow<List<AnimeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: AnimeModel)

    @Query("DELETE FROM Bookmark WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM Bookmark")
    suspend fun deleteAll()
}