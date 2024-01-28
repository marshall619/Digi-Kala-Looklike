package com.example.digikalatestononline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalatestononline.data.model.profile.FavItem
import com.example.digikalatestononline.repository.FavoriteListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteItemViewModel @Inject constructor(
    private val repository: FavoriteListRepository
) : ViewModel(){

    val allFavItems: Flow<List<FavItem>> = repository.allFavItems

    fun insertFavItem(item: FavItem) {
        viewModelScope.launch {
            repository.insertFavItem(item)
        }
    }

    fun deleteFavItem(item: FavItem) {
        viewModelScope.launch {
            repository.deleteFavItem(item)
        }
    }

    suspend fun clearFavItems() {
        viewModelScope.launch {
            repository.clearFavItems()
        }
    }

    fun isFavItemExist(favId: String): Flow<Boolean> = repository.isFavItemExist(favId)
}