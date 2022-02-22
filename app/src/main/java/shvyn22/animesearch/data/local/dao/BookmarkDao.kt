package shvyn22.animesearch.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import shvyn22.animesearch.data.local.model.Bookmark

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM Bookmark")
    fun getItems(): Observable<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Bookmark): Completable

    @Query("DELETE FROM Bookmark WHERE id = :id")
    fun delete(id: Int): Completable

    @Query("DELETE FROM Bookmark")
    fun deleteAll(): Completable
}