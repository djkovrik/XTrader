package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.provider.CurrencyProvider
import com.sedsoftware.core.test.blockingMemoized
import com.sedsoftware.core.test.get
import com.sedsoftware.core.utils.adapter.OffsetDateTimeAdapter
import com.sedsoftware.exchange.binance.Urls
import com.sedsoftware.exchange.binance.network.model.PairsInfo
import com.winterbe.expekt.should
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class BinanceSymbolsMapperSpec : Spek({

    val adapter = OffsetDateTimeAdapter()
    val response: PairsInfo? by blockingMemoized { get(Urls.GET_CURRENCY_PAIRS, adapter, PairsInfo::class.java) }

    describe("Check mapper") {

        val currencyProvider = object : CurrencyProvider {
            override suspend fun getCurrency(symbol: String): Currency =
                object : Currency {
                    override val name: String = "name"
                    override val label: String = "label"
                }
        }

        val mapper = BinanceSymbolsMapper(currencyProvider)

        it("Maps server time correctly") {
            response?.let { mapper.mapSyncInfoToDb(it).lastSyncDate.should.not.be.`null` }
        }

        it("Maps symbols correctly") {
            response?.let { pairsInfo ->
                runBlocking {
                    mapper.mapSymbolsToDb(pairsInfo).should.not.be.empty
                }
            }
        }
    }
})
