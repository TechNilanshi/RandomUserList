package com.example.randomuserapp.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.randomuserapp.data.pagingSource.UserPagingSource
import com.example.randomuserapp.data.remote.ApiService
import com.example.randomuserapp.domain.model.User
import com.example.randomuserapp.domain.repository.RandomUserListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RandomUserListRepositoryImpl @Inject constructor(
    private val apiService: ApiService // Retrofit API Service
) : RandomUserListRepository {
    /*override fun fetchUsers(): Flow<List<User>> = flow {
        try {
            // Fetch data from the API
            val response = apiService.getApiResponse()

            // Emit the list of users from the API response
            emit(response.results) // Extract 'results' which is a list of users
        } catch (e: Exception) {
            // Handle exceptions, such as network issues or parsing errors
            throw e
        }
    }.catch { e ->
// Handle errors and provide a fallback if necessary
        emit(emptyList()) // Emit an empty list in case of error
    }.flowOn(Dispatchers.IO) // Run the flow on a background thread (IO)*/
    override fun fetchUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50, // Load 50 items per page
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
    }


}