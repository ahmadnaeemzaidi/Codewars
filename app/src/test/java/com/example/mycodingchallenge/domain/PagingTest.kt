package com.example.mycodingchallenge.domain

import androidx.paging.PagingSource
import com.example.mycodingchallenge.data.ApiRepository
import com.example.mycodingchallenge.data.ChallengesSource
import com.example.mycodingchallenge.data.response.ChallengeListResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PagingTest {

    private val mockRepository = mockk<ApiRepository>()

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `should get same amount of result as specified in paging load`() = runBlocking {
        every { runBlocking { mockRepository.getChallenges(any()) } } returns FakeData.getFakeChallengesResponse()
        val pagingSource = ChallengesSource(mockRepository)
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = FakeData.getFakeChallengesResponse().data,
                prevKey = null,
                nextKey = 2
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `should get error if server gives error response`() = runBlocking {
        every { runBlocking { mockRepository.getChallenges(any()) } } returns ChallengeListResponse(0,0,
            arrayListOf()
        )
        val pagingSource = ChallengesSource(mockRepository)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        Assert.assertTrue(result is PagingSource.LoadResult.Error)
    }
}