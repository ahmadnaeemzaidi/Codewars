package com.example.mycodingchallenge.domain

import com.example.mycodingchallenge.data.response.ChallengeListData
import com.example.mycodingchallenge.data.response.ChallengeListResponse


object FakeData {
    fun getFakeChallengesResponse() = ChallengeListResponse(14, 266, getChallengesList())
    private fun getChallengesList(): ArrayList<ChallengeListData> {
        val challengesResults: ArrayList<ChallengeListData> = ArrayList()
        challengesResults.add(ChallengeListData(
            "56948e24b510d4e1d3000022",
            "AOP - Before",
            "aop-before",
            arrayListOf("javascript"),
            "2022-12-27T09:13:54.388Z"
        ))
        challengesResults.add(ChallengeListData(
            "56bac4c34537cf1e270005a1",
            "Sorting Arrays ... wait, what?!",
            "sorting-arrays-dot-dot-dot-wait-what",
            arrayListOf("javascript"),
            "2022-12-13T13:42:01.222Z"
        ))
        return challengesResults
    }
}