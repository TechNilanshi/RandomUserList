package com.example.randomuserapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.randomuserapp.domain.repository.RandomUserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserListDataViewModel @Inject constructor(private val randomUserListRepository: RandomUserListRepository) :
    ViewModel() {

/*    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState*/

    val usersFlow = randomUserListRepository.fetchUsers()
        .cachedIn(viewModelScope) // Cache data in memory while the ViewModel is active

    /* init {
         fetchUsers()
     }

      fun fetchUsers() {
         viewModelScope.launch {
             _uiState.value = _uiState.value.copy(isLoading = true, error = null)

             try {
                 randomUserListRepository.fetchUsers()
                     .collect { userList ->
                         _uiState.value = _uiState.value.copy(
                             users = userList,
                             isLoading = false,
                             error = null
                         )
                     }
             } catch (e: Exception) {
                 _uiState.value = _uiState.value.copy(
                     isLoading = false,
                     error = e.message ?: "An unexpected error occurred"
                 )
             }
         }
     }*/

}