package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: MockApi = com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1.mockApi()
) : BaseViewModel<UiState>() {

    fun performRequestWithCoroutine(){
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try{
                val recentAndroidVersions = mockApi.getRecentAndroidVersions()
                val lastAndridVersion = recentAndroidVersions.last()
                if(lastAndridVersion!=null){
                    val infoAboutLastVersion = mockApi.getAndroidVersionFeatures(lastAndridVersion.apiLevel)
                    uiState.value = UiState.Success(infoAboutLastVersion)
                }
            }catch (e: Exception){
                uiState.value = UiState.Error("Network failed error")
            }
        }
    }
}