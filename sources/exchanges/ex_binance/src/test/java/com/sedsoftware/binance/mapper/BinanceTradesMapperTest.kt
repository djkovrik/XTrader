package com.sedsoftware.binance.mapper

import com.sedsoftware.binance.database.model.BinanceTradeDbModel
import com.sedsoftware.binance.entity.BinanceCurrencyPairTrade
import com.sedsoftware.binance.fakedata.FakeTradesData
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class BinanceTradesMapperTest : Spek({

    describe("Symbol trades mapper") {

        val mapper by memoized { BinanceTradesMapper() }
        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeTradesData() }

        val parsedList = fakeDataSource.getPredefinedParsedEntities()

        lateinit var mappedDbEntities: List<BinanceTradeDbModel>

        context("Mapping list from parsed cloud to db") {
            mappedDbEntities = parsedList.map { mapper.mapFromCloudToDb(it) }

            it("should return correct list") {
                mappedDbEntities.should.equal(fakeDataSource.getPredefinedDatabaseEntities())
            }
        }

        lateinit var mappedDomainEntities: List<BinanceCurrencyPairTrade>

        context("Mapping list from db to entities") {
            mappedDomainEntities = mappedDbEntities.map { mapper.mapFromDbToEntity(it) }

            it("should return correct list") {
                mappedDomainEntities.should.equal(fakeDataSource.getPredefinedDomainEntities())
            }
        }
    }
})
