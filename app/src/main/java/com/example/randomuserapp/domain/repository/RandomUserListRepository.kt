package com.example.randomuserapp.domain.repository


import androidx.paging.PagingData
import com.example.randomuserapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RandomUserListRepository {
   // fun fetchUsers(): Flow<List<User>>
   // fun fetchUsers(page: Int, pageSize: Int): Flow<PagingData<User>>
  fun fetchUsers(): Flow<PagingData<User>>


}