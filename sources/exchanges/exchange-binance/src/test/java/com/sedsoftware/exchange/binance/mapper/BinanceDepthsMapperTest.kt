package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.core.domain.entity.ExchangeType
import com.sedsoftware.exchange.binance.database.model.BinanceDepthDbModel
import com.sedsoftware.exchange.binance.entity.BinanceCurrency
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPair
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPairDepth
import com.sedsoftware.exchange.binance.fakedata.FakeDepthsData
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class BinanceDepthsMapperTest : Spek({

    describe("Symbol info mapper") {

        val currencyPair = BinanceCurrencyPair(
            exchange = ExchangeType.BINANCE,
            baseCurrency = BinanceCurrency.LTC,
            marketCurrency = BinanceCurrency.BTC,
            symbol = "LTCBTC"
        )

        val mapper by memoized { BinanceDepthsMapper() }
        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeDepthsData() }
        val parsedDto = fakeDataSource.getPredefinedParsedDto()

        lateinit var mappedDbEntities: List<BinanceDepthDbModel>

        context("Mapping from parsed cloud to db") {
            mappedDbEntities = mapper.mapFromCloudToDb(currencyPair, parsedDto)

            it("should return correct values") {
                mappedDbEntities.should.equal(fakeDataSource.getPredefinedDatabaseEntities(currencyPair))
            }
        }

        lateinit var mappedDomainEntities: List<BinanceCurrencyPairDepth>

        context("Mapping from db to entities") {
            mappedDomainEntities = mappedDbEntities.map { mapper.mapFromDbToEntity(it) }
            val predefinedEntities = fakeDataSource.getPredefinedDomainEntities(currencyPair)

            it("should return correct values") {
                mappedDomainEntities.should.equal(predefinedEntities)
            }
        }
    }
})
