package com.example.digikalatestononline.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.data.model.profile.FavItem
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteListDao {
    @Query("select * from favorite_list_table")
    fun getFavoriteItems() : Flow<List<FavItem>>
    @Delete
    suspend fun deleteFavItem(item : FavItem)

    @Insert
    suspend fun insertFavItem(item: FavItem)

    @Query("delete from favorite_list_table")
    suspend fun clearFavItem()

    @Query("select exists(select * from favorite_list_table where id = :favId)")
    fun isFavItemExist(favId : String) : Flow<Boolean>
}