package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase6

import com.lukaslechner.coroutineusecasesonandroid.usecases.flow.mock.FlowMockApi
import com.lukaslechner.coroutineusecasesonandroid.usecases.flow.mock.Stock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

interface StockPriceDataSource {
    val latestPrice: Flow<List<Stock>>
}

class NetworkStockPriceDataSource(mockApi: FlowMockApi) : StockPriceDataSource {

    override val latestPrice: Flow<List<Stock>> = flow {
        while (true) {
            val currentStockList = mockApi.getCurrentStockPrices()

            Timber.d("Emitting new stock list")
            emit(currentStockList)

            delay(5_000)
        }
    }
}
