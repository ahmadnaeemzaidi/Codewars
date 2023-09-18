package com.example.mycodingchallenge.data

import com.example.mycodingchallenge.data.di.SafeApiRequest


class ApiRepository(private  val apiService: ApiService): SafeApiRequest() {

    suspend fun getChallenges(pageNumber: Int) = apiRequest {
        apiService.getChallenges(pageNumber)
    }

}