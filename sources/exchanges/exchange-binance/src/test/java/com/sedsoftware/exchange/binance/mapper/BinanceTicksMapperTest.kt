package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.exchange.binance.database.model.BinanceTickDbModel
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPairTick
import com.sedsoftware.exchange.binance.fakedata.FakeTickData
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class BinanceTicksMapperTest : Spek({

    describe("Symbol tick mapper") {

        val mapper by memoized { BinanceTicksMapper() }
        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeTickData() }

        val parsedEntity = fakeDataSource.getPredefinedParsedEntity()

        lateinit var mappedDbEntity: BinanceTickDbModel

        context("Mapping from parsed cloud to db") {
            mappedDbEntity = mapper.mapFromCloudToDb(parsedEntity)

            it("should return correct value") {
                mappedDbEntity.should.equal(fakeDataSource.getPredefinedDatabaseEntity())
            }
        }

        lateinit var mappedDomainEntity: BinanceCurrencyPairTick

        context("Mapping from db to entity") {
            mappedDomainEntity = mapper.mapFromDbToEntity(mappedDbEntity)

            it("should return correct value") {
                mappedDomainEntity.should.equal(fakeDataSource.getPredefinedDomainEntity())
            }
        }
    }
})
