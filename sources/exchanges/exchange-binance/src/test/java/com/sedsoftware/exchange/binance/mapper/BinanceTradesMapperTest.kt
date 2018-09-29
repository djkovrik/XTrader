package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.core.domain.entity.ExchangeType
import com.sedsoftware.exchange.binance.database.model.BinanceTradeDbModel
import com.sedsoftware.exchange.binance.entity.BinanceCurrency.BTC
import com.sedsoftware.exchange.binance.entity.BinanceCurrency.LTC
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPair
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPairTrade
import com.sedsoftware.exchange.binance.fakedata.FakeTradesData
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class BinanceTradesMapperTest : Spek({

    describe("Symbol trades mapper") {

        val mapper by memoized { BinanceTradesMapper() }
        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeTradesData() }
        val testCurrency by memoized {
            BinanceCurrencyPair(
                exchange = ExchangeType.BINANCE,
                baseCurrency = LTC,
                marketCurrency = BTC,
                symbol = "LTCBTC"
            )
        }

        val parsedList = fakeDataSource.getPredefinedParsedEntities()

        lateinit var mappedDbEntities: List<BinanceTradeDbModel>

        context("Mapping list from parsed cloud to db") {
            mappedDbEntities = parsedList.map { mapper.mapFromCloudToDb(testCurrency, it) }

            it("should return correct list") {
                mappedDbEntities.should.equal(fakeDataSource.getPredefinedDatabaseEntities())
            }
        }

        lateinit var mappedDomainEntities: List<BinanceCurrencyPairTrade>

        context("Mapping list from db to entities") {
            mappedDomainEntities = mappedDbEntities.map { mapper.mapFromDbToEntity(testCurrency, it) }

            it("should return correct list") {
                mappedDomainEntities.should.equal(fakeDataSource.getPredefinedDomainEntities())
            }
        }
    }
})
