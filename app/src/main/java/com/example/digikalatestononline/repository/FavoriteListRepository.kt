package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.db.FavoriteListDao
import com.example.digikalatestononline.data.model.profile.FavItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteListRepository @Inject constructor(
    private val dao: FavoriteListDao
) {

    val allFavItems: Flow<List<FavItem>> = dao.getFavoriteItems()

    suspend fun insertFavItem(item: FavItem) {
        dao.insertFavItem(item)
    }

    suspend fun deleteFavItem(item: FavItem) {
        dao.deleteFavItem(item)
    }

    suspend fun clearFavItems() {
        dao.clearFavItem()
    }

    fun isFavItemExist(favId: String): Flow<Boolean> = dao.isFavItemExist(favId)
}