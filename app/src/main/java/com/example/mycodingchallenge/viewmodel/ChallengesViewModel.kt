package com.example.mycodingchallenge.viewmodel

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mycodingchallenge.data.ApiRepository
import com.example.mycodingchallenge.data.ChallengesSource
import com.example.mycodingchallenge.data.exceptions.ApiException
import com.example.mycodingchallenge.data.exceptions.NoInternetException
import com.example.mycodingchallenge.data.response.ChallengeDetailsResponse
import com.example.mycodingchallenge.data.response.ChallengeListData
import com.example.mycodingchallenge.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ChallengesViewModel @Inject constructor(
    private val challengesSource: ChallengesSource,
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _challengeDetailState = MutableStateFlow(ChallengeDetailsResponse(progress = true))
    val challengeDetailState: StateFlow<ChallengeDetailsResponse>
        get() = _challengeDetailState

    fun getAllChallenges(): Flow<PagingData<ChallengeListData>> {
        return Pager(PagingConfig(AppConstants.PAGE_SIZE)) { challengesSource }.flow.cachedIn(viewModelScope)
    }

    fun getChallengeDetails(challengeId: String){
        viewModelScope.launch {
            try{

                var response = apiRepository.getChallengeDetails(challengeId)
                response.let {
                    _challengeDetailState.value = response
                }
            }catch (e: ApiException) {
                _challengeDetailState.value = ChallengeDetailsResponse(error = e.message!!, progress = false)
            } catch (e: NoInternetException) {
                _challengeDetailState.value = ChallengeDetailsResponse(error = e.message!!, progress = false)
            } catch (e: IOException) {
                _challengeDetailState.value = ChallengeDetailsResponse(error = e.message!!, progress = false)

            }
        }
    }


}