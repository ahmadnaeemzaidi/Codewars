package com.example.mycodingchallenge.repository

import com.example.mycodingchallenge.data.ApiRepository
import com.example.mycodingchallenge.repository.fakeimplementation.success.FakeSuccessApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class ApiRepositoryTest {

    @Test
    fun `should get challenges List when server gives success response`() = runBlocking {
        val repository: ApiRepository = ApiRepository(FakeSuccessApi())
        val result = repository.getChallenges(1)
        Assert.assertTrue(result.totalPages == 14)
        Assert.assertTrue(result.data != null)
    }

    @Test
    fun `should get challenge details when server gives success response`() = runBlocking {
        val repository: ApiRepository = ApiRepository(FakeSuccessApi())
        val result = repository.getChallengeDetails("56f8fe6a2e6c0dc83b0008a7")
        Assert.assertTrue(result.id !=null )
    }

}