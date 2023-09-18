package com.example.mycodingchallenge.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mycodingchallenge.data.exceptions.ApiException
import com.example.mycodingchallenge.data.exceptions.NoInternetException
import com.example.mycodingchallenge.data.response.ChallengeListData
import java.io.IOException
import javax.inject.Inject

class ChallengesSource @Inject constructor(private val apiRepository: ApiRepository): PagingSource<Int, ChallengeListData>() {

    override fun getRefreshKey(state: PagingState<Int, ChallengeListData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChallengeListData> {
        val nextPage = params.key ?: 1
        try{
            val challengeResponse = apiRepository.getChallenges(nextPage-1)
            return if (challengeResponse.data == null || challengeResponse.data.isEmpty()) {
                LoadResult.Error(ApiException("End of list reached"))
            } else {
                LoadResult.Page(
                    data = challengeResponse.data,
                    prevKey = if (nextPage == 1) null else nextPage-1,
                    nextKey = nextPage.plus(1)
                )
            }
        }catch (e: ApiException) {
           return LoadResult.Error(e)
        } catch (e: NoInternetException) {
            return LoadResult.Error(e)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        }

    }
}