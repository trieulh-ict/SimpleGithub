package io.trieulh.simplegithub.journey.dashboard.screens.search

import io.mockk.every
import io.mockk.mockk
import io.trieulh.simplegithub.base.BaseTest
import io.trieulh.simplegithub.utils.TestDispatcherFactory
import io.trieulh.simpledomain.model.SearchUsers
import io.trieulh.simpledomain.model.UserItem
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simpledomain.usecase.base.SearchUsersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest : BaseTest() {

    private lateinit var viewModel: SearchViewModel

    private val searchUsersUseCase: SearchUsersUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = SearchViewModel(TestDispatcherFactory, searchUsersUseCase)
    }

    @Test
    fun test_submitSearchInput_Empty() {
        // Given
        every { searchUsersUseCase.execute(any()) } returns flowOf(
            DataState.Result(
                SearchUsers(
                    items = listOf(UserItem(id = 1L, login = "sample", avatarURL = "http://sample.com/image.png"))
                )
            )
        )
        // When
        runBlocking {
            viewModel.searchUsers("")
        }
        // Then
        Assert.assertTrue((viewModel.listUsersFlow.value as? DataState.Result)?.data?.size == 0)
    }

    @Test
    fun test_submitSearchInput_Result() {
        // Given
        every { searchUsersUseCase.execute(any()) } returns flowOf(
            DataState.Result(
                SearchUsers(
                    items = listOf(UserItem(id = 1L, login = "sample", avatarURL = "http://sample.com/image.png"))
                )
            )
        )
        // When
        runBlocking {
            viewModel.searchUsers("sample")
        }
        // Then
        Assert.assertTrue((viewModel.listUsersFlow.value as? DataState.Result)?.data?.size == 1)
    }

    @Test
    fun test_submitSearchInput_Failed() {
        // Given
        every { searchUsersUseCase.execute(any()) } returns flowOf(
            DataState.Failed(Error("error message"))
        )
        // When
        runBlocking {
            viewModel.searchUsers("sample")
        }
        // Then
        Assert.assertTrue(viewModel.listUsersFlow.value is DataState.Failed)
    }

    @Test
    fun test_loadMoreUsers() {
        // Given
        every { searchUsersUseCase.execute(any()) } returns flowOf(
            DataState.Result(
                SearchUsers(
                    items = listOf(UserItem(id = 1L, login = "sample", avatarURL = "http://sample.com/image.png"))
                )
            )
        )
        // When
        runBlocking {
            viewModel.searchUsers("sample")
        }

        // Given
        every { searchUsersUseCase.execute(any()) } returns flowOf(
            DataState.Result(
                SearchUsers(
                    items = listOf(UserItem(id = 2L, login = "sample", avatarURL = "http://sample.com/image.png"))
                )
            )
        )
        // When
        runBlocking {
            viewModel.loadMoreUsers()
        }
        // Then
        Assert.assertTrue((viewModel.listUsersFlow.value as? DataState.Result)?.data?.size == 2)
    }
}