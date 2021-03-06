package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import com.sedsoftware.core.domain.repository.TickerRepository
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceTicksDao
import com.sedsoftware.exchange.binance.database.model.BinancePairTickDbModel
import com.sedsoftware.exchange.binance.mapper.BinanceSymbolsMapper
import com.sedsoftware.exchange.binance.network.BinanceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class BinanceTickerRepository @Inject constructor(
    private val api: BinanceApi,
    private val db: BinanceDatabase,
    private val mapper: BinanceSymbolsMapper
) : TickerRepository {

    private val dao: BinanceTicksDao by lazy {
        db.getBinanceTicksDao()
    }

    override suspend fun hasTickers(): Boolean =
        dao.getFirstTick() != null

    override suspend fun fetchPrice(pair: CurrencyPair): Float {
        val tick = api.getPairTick(pair.symbol)
        return tick.price.toFloat()
    }

    override suspend fun refreshPrice(pair: CurrencyPair, price: Float) {
        val now = OffsetDateTime.now()
        val insertionDate = dao.getInsertionDate(pair.symbol) ?: now

        val previousPrice = dao.getPreviousPrice(pair.symbol) ?: 0f
        val currentPricePercent = price * BASE_PERCENT_VALUE / previousPrice
        val percentChange = currentPricePercent - BASE_PERCENT_VALUE
        val valueChange = price - previousPrice

        dao.insert(
            BinancePairTickDbModel(
                symbol = pair.symbol,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                quoteCurrencyName = pair.marketCurrency.name,
                quoteCurrencyLabel = pair.marketCurrency.label,
                previousPrice = previousPrice,
                currentPrice = price,
                percentChange = percentChange,
                valueChange = valueChange,
                insertionDate = insertionDate,
                refreshDate = now
            )
        )
    }

    override suspend fun addPairToWatchList(pair: CurrencyPair) {
        val now = OffsetDateTime.now()
        val insertionDate = dao.getInsertionDate(pair.symbol) ?: now

        dao.insert(
            BinancePairTickDbModel(
                symbol = pair.symbol,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                quoteCurrencyName = pair.marketCurrency.name,
                quoteCurrencyLabel = pair.marketCurrency.label,
                previousPrice = 0f,
                currentPrice = 0f,
                percentChange = 0f,
                valueChange = 0f,
                insertionDate = insertionDate,
                refreshDate = now
            )
        )
    }

    override suspend fun removePairFromWatchList(pair: CurrencyPair) {
        dao.delete(pair.symbol)
    }

    override suspend fun getCurrentWatchList(): List<CurrencyPairTick> =
        dao.getTicks().map { mapper.mapDbTickToEntity(it) }


    override suspend fun watchForTickers(): Flow<List<CurrencyPairTick>> =
        dao.getTicksFlow().map { list -> list.map { mapper.mapDbTickToEntity(it) } }

    private companion object {
        const val BASE_PERCENT_VALUE = 100f
    }
}
