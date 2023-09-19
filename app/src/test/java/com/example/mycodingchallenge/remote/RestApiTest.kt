package com.example.mycodingchallenge.remote

import com.example.mycodingchallenge.data.exceptions.ApiException
import com.example.mycodingchallenge.data.exceptions.NoInternetException
import com.example.mycodingchallenge.data.response.ChallengeDetailsResponse
import com.example.mycodingchallenge.data.response.ChallengeListResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

class RestApiTest: BaseTest() {

    @Test
    fun `should get all challenges when server gives success response`() = runBlocking {
        val expectedResponse = getExpectedResponse("get_all_challenges_success_response.json", ChallengeListResponse::class.java)
        getResponse("get_all_challenges_success_response.json", HttpURLConnection.HTTP_OK)
        val result = apiRepository.getChallenges(1)
        Assert.assertEquals(expectedResponse.data.size, result.data.size)
    }

    @Test
    fun `should throw client exception when server sends 4xx response`() {
        Assert.assertThrows(ApiException::class.java) {
            runBlocking {
                getResponse("get_all_challenges_success_response.json", HttpURLConnection.HTTP_BAD_REQUEST)
                apiRepository.getChallenges(1)
            }
        }
    }

    @Test
    fun `should throw no network exception in case of timeout`() {
        Assert.assertThrows(SocketTimeoutException::class.java) {
            runBlocking {
                getTimeout()
                apiRepository.getChallenges(1)
            }
        }
    }

    @Test
    fun `should get challenge details when server gives success response`() = runBlocking {
        val expectedResponse = getExpectedResponse("get_challenge_details_success_response.json", ChallengeDetailsResponse::class.java)
        getResponse("get_challenge_details_success_response.json", HttpURLConnection.HTTP_OK)
        val result = apiRepository.getChallengeDetails("56f8fe6a2e6c0dc83b0008a7")
        Assert.assertEquals(expectedResponse.id, result.id)
    }
}