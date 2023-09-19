package com.example.mycodingchallenge.data

import com.example.mycodingchallenge.data.response.ChallengeDetailsResponse
import com.example.mycodingchallenge.data.response.ChallengeListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/users/g964/code-challenges/completed")
    suspend fun getChallenges(@Query("page") page: Int) : Response<ChallengeListResponse>

    @GET("api/v1/code-challenges/{challenge_id}")
    suspend fun getChallengeDetail(@Path("challenge_id") challengeId: String) : Response<ChallengeDetailsResponse>

}