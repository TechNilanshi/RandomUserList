package com.example.randomuserapp

import androidx.paging.PagingData
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
import com.example.randomuserapp.presentation.viewModel.UserListDataViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
//import app.cash.turbine.test


@ExperimentalCoroutinesApi
class RandomUserViewModelTest {

    // Dependencies
    private lateinit var viewModel: UserListDataViewModel
    private lateinit var repository: RandomUserListRepository

    // Rule for Coroutines Testing
    @get:Rule
    val coroutinesTestRule = MainDispatcherRule()

    @Before
    fun setUp() {
        // Mock the repository
        repository = mockk()

        // Initialize the ViewModel with the mocked repository
        viewModel = UserListDataViewModel(repository)
    }

    @Test
    fun testUserFlowData() = runTest {
        // Create mock PagingData
        val mockPagingData = PagingData.from(
            listOf(
                User(
                    gender = "male",
                    name = Name("Mr", "John ", "Doe"),
                    location = Location(
                        Street(123, ""),
                        "City ",
                        "State",
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
                ),
                User(
                    gender = "male",
                    name = Name("Mr", "JohnB", "Doe"),
                    location = Location(
                        Street(123, ""),
                        "City ",
                        "State ",
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
            )
        )

        // Mock repository behavior
        every { repository.fetchUsers() } returns flowOf(mockPagingData)


    }

}