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
    fun getBookmarks(): Observable<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(item: Bookmark): Completable

    @Query("DELETE FROM Bookmark WHERE id = :id")
    fun deleteBookmark(id: Int): Completable

    @Query("DELETE FROM Bookmark")
    fun deleteBookmarks(): Completable
}