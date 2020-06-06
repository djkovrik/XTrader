package com.sedsoftware.exchange.bitfinex.repository

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import com.sedsoftware.core.domain.repository.PairsTickRepository
import com.sedsoftware.exchange.bitfinex.database.BitfinexDatabase
import com.sedsoftware.exchange.bitfinex.database.dao.BitfinexTicksDao
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexPairTickDbModel
import com.sedsoftware.exchange.bitfinex.mapper.BitfinexSymbolsMapper
import com.sedsoftware.exchange.bitfinex.network.BitfinexApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class BitfinexPairTicksRepository @Inject constructor(
    private val api: BitfinexApi,
    private val db: BitfinexDatabase,
    private val mapper: BitfinexSymbolsMapper
) : PairsTickRepository {

    private val dao: BitfinexTicksDao by lazy {
        db.getBitfinexTicksDao()
    }

    override suspend fun hasTicks(): Boolean =
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
            BitfinexPairTickDbModel(
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
            BitfinexPairTickDbModel(
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


    override suspend fun watchForTicks(): Flow<List<CurrencyPairTick>> =
        dao.getTicksFlow().map { list -> list.map { mapper.mapDbTickToEntity(it) } }

    private companion object {
        const val BASE_PERCENT_VALUE = 100f
    }
}
