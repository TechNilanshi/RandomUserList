package com.example.randomuserapp.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.randomuserapp.data.pagingSource.UserPagingSource
import com.example.randomuserapp.data.remote.ApiService
import com.example.randomuserapp.domain.model.User
import com.example.randomuserapp.domain.repository.RandomUserListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RandomUserListRepositoryImpl @Inject constructor(
    private val apiService: ApiService // Retrofit API Service
) : RandomUserListRepository {
    override fun fetchUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // Load 10 items per page
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
    }
}