package com.example.mycodingchallenge.data.response

import com.google.gson.annotations.SerializedName

class ChallengeDetailsResponse(
    var error: String = "",
    var progress: Boolean = false
){
    var id: String = ""
    var name: String = ""
    var slug: String = ""
    var category: String = ""
    var publishedAt: String = ""
    var approvedAt: String = ""
    var languages: ArrayList<String> = arrayListOf()
    var url: String = ""
    var rank: ChallengeRank? = ChallengeRank()
    var createdAt: String = ""
    var createdBy: ChallengeCreatedBy? = ChallengeCreatedBy()
    var approvedBy: ChallengeApprovedBy? = ChallengeApprovedBy()
    var description: String = ""
    var totalAttempts: String = ""
    var totalCompleted: String = ""
    var totalStars: String = ""
    var voteScore: String = ""
    var tags: ArrayList<String> = arrayListOf()
    var contributorsWanted: Boolean? = false
    var unresolved: ChallengeUnresolved? = ChallengeUnresolved()
}

data class ChallengeRank(

    var id: Int? = null,
    var name: String? = null,
    var color: String? = null

)

data class ChallengeCreatedBy(

    var username: String? = null,
    var url: String? = null

)

data class ChallengeApprovedBy(
    var username: String? = null,
    var url: String? = null

)


data class ChallengeUnresolved (

    var issues      : Int? = null,
    var suggestions : Int? = null

)