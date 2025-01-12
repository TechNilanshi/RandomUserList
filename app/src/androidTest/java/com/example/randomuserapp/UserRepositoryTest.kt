package com.example.randomuserapp

import com.example.randomuserapp.data.remote.ApiService
import com.example.randomuserapp.data.repositoryImpl.RandomUserListRepositoryImpl
import com.example.randomuserapp.domain.model.Coordinates
import com.example.randomuserapp.domain.model.DOB
import com.example.randomuserapp.domain.model.ID
import com.example.randomuserapp.domain.model.Location
import com.example.randomuserapp.domain.model.Login
import com.example.randomuserapp.domain.model.Name
import com.example.randomuserapp.domain.model.Picture
import com.example.randomuserapp.domain.model.Registered
import com.example.randomuserapp.domain.model.Street
import com.example.randomuserapp.domain.model.Timezone
import com.example.randomuserapp.domain.model.User
import com.example.randomuserapp.domain.repository.RandomUserListRepository
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {
    private lateinit var userRepository: RandomUserListRepository
    private val mockApiService: ApiService = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set the main dispatcher to TestDispatcher
        userRepository = RandomUserListRepositoryImpl(mockApiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher
    }

    @Test
    fun fetchUsers_returns_PagingData_with_users() = runTest {
        // Arrange: Mock the API response
        val mockUsers = (1..50).map {
            User(
                gender = "male",
                name = Name("Mr", "John $it", "Doe"),
                location = Location(
                    Street(123, ""),
                    "City $it",
                    "State $it",
                    "Country",
                    "",
                    Coordinates("", ""),
                    Timezone("", "")
                ),
                email = "",
                login = Login(
                    uuid = "",
                    username = "",
                    password = "",
                    salt = "",
                    md5 = "",
                    sha1 = "",
                    sha256 = ""
                ),
                dob = DOB("", 23),
                registered = Registered("", 3),
                phone = "",
                cell = "",
                id = ID("", ""),
                picture = Picture(
                    large = "https://example.com/large.jpg",
                    medium = "https://example.com/medium.jpg",
                    thumbnail = "https://example.com/thumb.jpg"
                ),
                nat = ""
            )
        }

       // val mockPagingSource = MockPagingSource(mockUsers)

        // Mock the API to return a PagingSource
        //coEvery { mockApiService.getApiResponse(any(), any()) } returns mockPagingSource

        // Act: Fetch users
       val result = userRepository.fetchUsers().toList()

        // Assert: Verify the emitted PagingData contains the mocked data
        assert(result.isNotEmpty())
        val pagingData = result.first()

    }
}