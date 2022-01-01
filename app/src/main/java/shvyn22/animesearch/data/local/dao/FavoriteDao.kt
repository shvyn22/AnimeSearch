package shvyn22.animesearch.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import shvyn22.animesearch.data.local.model.AnimeModel

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM Favorite")
    suspend fun getItems(): Flow<List<AnimeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: AnimeModel)

    @Query("DELETE FROM Favorite WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM Favorite")
    suspend fun deleteAll()
}