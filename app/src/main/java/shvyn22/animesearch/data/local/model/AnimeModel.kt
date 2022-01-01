package shvyn22.animesearch.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class AnimeModel(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "isAdult")
    val isAdult: Boolean,
)
