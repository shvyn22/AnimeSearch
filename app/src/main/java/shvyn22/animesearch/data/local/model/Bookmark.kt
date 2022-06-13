package shvyn22.animesearch.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bookmark")
data class Bookmark(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "isAdult")
    val isAdult: Boolean,

    @ColumnInfo(name = "image")
    val image: String,
)