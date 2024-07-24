package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class PerformNetworkRequestsConcurrentlyViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val oreoFeature = mockApi.getAndroidVersionFeatures(27)
                val pieFeature = mockApi.getAndroidVersionFeatures(28)
                val android10Features = mockApi.getAndroidVersionFeatures(29)

                val versonFeatures = listOf(oreoFeature, pieFeature, android10Features)
                uiState.value = UiState.Success(versonFeatures)
            }catch (e:Exception){
                uiState.value = UiState.Error("Network failed")
            }

        }
    }

    fun performNetworkRequestsConcurrently() {
        uiState.value = UiState.Loading
        val deffered1 = viewModelScope.async {
            val oreoFeature = mockApi.getAndroidVersionFeatures(27)
            oreoFeature
        }

        val deffered2 = viewModelScope.async {
            val pieFeature = mockApi.getAndroidVersionFeatures(28)
            pieFeature
        }

        val deffered3 = viewModelScope.async {
            val android10Features = mockApi.getAndroidVersionFeatures(29)
            android10Features
        }

        viewModelScope.launch {
            try {
                val versionFeatures = awaitAll(deffered1, deffered2, deffered3)
//                val resultList = listOf()
                uiState.value = UiState.Success(versionFeatures)
            }catch(e:Exception){
                uiState.value = UiState.Error("Error")
            }
        }
    }
}