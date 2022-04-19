package io.trieulh.simplegithub.journey.dashboard.screens.detail

import io.mockk.every
import io.mockk.mockk
import io.trieulh.simplegithub.base.BaseTest
import io.trieulh.simplegithub.utils.TestDispatcherFactory
import io.trieulh.simpledomain.model.User
import io.trieulh.simpledomain.model.base.DataState
import io.trieulh.simpledomain.usecase.base.GetUserDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest : BaseTest() {

    private lateinit var viewModel: DetailViewModel

    private val getUserDetailUseCase: GetUserDetailUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(TestDispatcherFactory, getUserDetailUseCase)
    }

    @Test
    fun test_getUserDetail_Result() {
        // Given
        every { getUserDetailUseCase.execute(any()) } returns flowOf(DataState.Result(User(login = "sample")))

        // When
        runBlocking {
            viewModel.getUserDetail(login = "sample")
        }

        // Then
        Assert.assertTrue((viewModel.userDetail.value as? DataState.Result)?.data?.login == "sample")
    }

    @Test
    fun test_getUserDetail_Loading() {
        // Given
        every { getUserDetailUseCase.execute(any()) } returns flowOf(DataState.Loading(User(login = "sample")))

        // When
        runBlocking {
            viewModel.getUserDetail(login = "sample")
        }

        // Then
        Assert.assertTrue((viewModel.userDetail.value as? DataState.Result)?.data?.login == "sample")
    }

    @Test
    fun test_getUserDetail_Failed() {
        // Given
        every { getUserDetailUseCase.execute(any()) } returns flowOf(DataState.Failed(Error("error message")))

        // When
        runBlocking {
            viewModel.getUserDetail(login = "sample")
        }

        // Then
        Assert.assertTrue(viewModel.userDetail.value is DataState.Failed)
    }
}