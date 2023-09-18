package com.example.mycodingchallenge.data.response

data class ChallengeListResponse(
    var totalPages : Int?            = null,
    var totalItems : Int?            = null,
    var data       : ArrayList<ChallengeListData> = arrayListOf())

data class ChallengeListData(
    var id: String? = null,
    var name: String? = null,
    var slug: String? = null,
    var completedLanguages: ArrayList<String> = arrayListOf(),
    var completedAt: String? = null
)