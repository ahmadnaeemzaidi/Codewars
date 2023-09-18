package com.example.mycodingchallenge.viewmodel

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mycodingchallenge.data.ApiRepository
import com.example.mycodingchallenge.data.ChallengesSource
import com.example.mycodingchallenge.data.response.ChallengeListData
import com.example.mycodingchallenge.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class ChallengesViewModel @Inject constructor(
    private val challengesSource: ChallengesSource
) : ViewModel() {


    fun getAllChallenges(): Flow<PagingData<ChallengeListData>> {
        return Pager(PagingConfig(AppConstants.PAGE_SIZE)) { challengesSource }.flow.cachedIn(viewModelScope)
    }


}