package shvyn22.animesearch.repository.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shvyn22.animesearch.data.local.dao.FavoriteDao
import shvyn22.animesearch.data.local.model.AnimeModel
import shvyn22.animesearch.util.Resource

class LocalRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : LocalRepository<AnimeModel> {

    override suspend fun getItems(): Flow<Resource<List<AnimeModel>>> =
        favoriteDao.getItems().map { items ->
            if (items.isEmpty()) Resource.Error("")
            else Resource.Success(items)
        }

    override suspend fun insertItem(item: AnimeModel) = favoriteDao.insert(item)

    override suspend fun deleteItem(id: Int) = favoriteDao.delete(id)

    override suspend fun deleteItems() = favoriteDao.deleteAll()
}