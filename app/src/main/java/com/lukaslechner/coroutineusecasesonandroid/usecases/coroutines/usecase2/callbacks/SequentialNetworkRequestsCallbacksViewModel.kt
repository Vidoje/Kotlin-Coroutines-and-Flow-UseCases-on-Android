package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        val getAndroidVersionsCall = mockApi.getRecentAndroidVersions()
        getAndroidVersionsCall.enqueue(object: Callback<List<AndroidVersion>>{
            override fun onResponse(
                call: Call<List<AndroidVersion>>,
                response: Response<List<AndroidVersion>>
            ) {
                if(response.isSuccessful){
                    val mostRecentVersion = response.body()!!.last()
                    val getAndroidFeaturesCall = mockApi.getAndroidVersionFeatures(mostRecentVersion.apiLevel)
                    getAndroidFeaturesCall.enqueue(object: Callback<VersionFeatures>{
                        override fun onResponse(
                            call: Call<VersionFeatures>,
                            response: Response<VersionFeatures>
                        ) {
                            if(response.isSuccessful){
                                val featuresOfMostRecentVersions = response.body()!!
                                uiState.value = UiState.Success(featuresOfMostRecentVersions)
                            }else{
                                uiState.value = UiState.Error("Something wrong happened not Failure 2")
                            }
                        }

                        override fun onFailure(p0: Call<VersionFeatures>, p1: Throwable) {
                            uiState.value = UiState.Error("Something wrong happened not Failure 1")
                        }

                    })
                }else{
                    uiState.value = UiState.Error("Something wrong happened not")
                }
            }

            override fun onFailure(p0: Call<List<AndroidVersion>>, p1: Throwable) {
                uiState.value = UiState.Error("Something wrong happened")
            }

        })
    }
}