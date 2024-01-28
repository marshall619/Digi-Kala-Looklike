package com.example.digikalatestononline.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart : CartItem)

    @Query("select * from shopping_cart where cartStatus =:status")
    fun getAllItems(status : CartStatus) : Flow<List<CartItem>>

    @Delete
    suspend fun removeFromCart(item :CartItem)

    @Query("update shopping_cart set count =:newCount where id=:id")
    suspend fun changeCountCartItem(id : String , newCount : Int)

    @Query("update shopping_cart set cartStatus =:newStatus where id=:id")
    suspend fun changeStatusCart(id : String , newStatus : CartStatus)

    @Query("select total(count) as count from shopping_cart where cartStatus =:cartStatus")
    fun getCartItemCount(cartStatus: CartStatus) : Flow<Int>

    @Query("DELETE FROM shopping_cart where cartStatus=:status")
    fun deleteAllItems(status: CartStatus)


    @Query("select total(count) as count from shopping_cart where id = :itemId")
    fun getItemsCountInBasket(itemId: String): Flow<Int>


    @Query("SELECT EXISTS(SELECT * FROM shopping_cart WHERE id = :itemId)")
    fun isItemExistInBasket(itemId: String): Flow<Boolean>


}