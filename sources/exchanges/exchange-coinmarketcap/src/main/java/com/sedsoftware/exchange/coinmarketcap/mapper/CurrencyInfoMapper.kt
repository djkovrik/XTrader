package com.sedsoftware.exchange.coinmarketcap.mapper

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.exchange.coinmarketcap.database.model.CurrencyDbModel
import com.sedsoftware.exchange.coinmarketcap.database.model.CurrencySyncInfoDbModel
import com.sedsoftware.exchange.coinmarketcap.network.model.CurrencyItem
import com.sedsoftware.exchange.coinmarketcap.network.model.CurrencyMap
import javax.inject.Inject

class CurrencyInfoMapper @Inject constructor() {

    fun mapSyncInfoToDb(from: CurrencyMap): CurrencySyncInfoDbModel =
        CurrencySyncInfoDbModel(
            count = from.data.size,
            timestamp = from.status.timestamp
        )

    fun mapToDb(from: CurrencyMap): List<CurrencyDbModel> =
        from.data.map { mapItem(it) }

    fun mapDbToCurrency(from: CurrencyDbModel): Currency =
        object : Currency {
            override val name: String = from.symbol
            override val label: String = from.name
        }

    private fun mapItem(item: CurrencyItem): CurrencyDbModel =
        CurrencyDbModel(
            symbol = item.symbol,
            name = item.name,
            isActive = item.isActive
        )
}
