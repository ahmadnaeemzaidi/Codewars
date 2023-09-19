package com.example.mycodingchallenge.repository.fakeimplementation.success

import com.example.mycodingchallenge.data.ApiService
import com.example.mycodingchallenge.data.response.ChallengeDetailsResponse
import com.example.mycodingchallenge.data.response.ChallengeListResponse
import com.example.mycodingchallenge.domain.FakeData.getFakeChallengesResponse
import retrofit2.Response

class FakeSuccessApi: ApiService {


    override suspend fun getChallenges(page: Int): Response<ChallengeListResponse> {
        return Response.success(getFakeChallengesResponse())
    }

    override suspend fun getChallengeDetail(challengeId: String): Response<ChallengeDetailsResponse> {
        return Response.success(ChallengeDetailsResponse())
    }
}