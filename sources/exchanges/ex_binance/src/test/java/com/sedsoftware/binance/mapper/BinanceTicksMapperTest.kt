package com.sedsoftware.binance.mapper

import com.sedsoftware.binance.database.model.BinanceTickDbModel
import com.sedsoftware.binance.entity.BinanceCurrencyPairTick
import com.sedsoftware.binance.fakedata.FakeTickData
import com.sedsoftware.binance.network.model.SymbolTickModel
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
                mappedDbEntity.should.equal(fakeDataSource.getDatabaseEntity())
            }
        }

        lateinit var mappedDomainEntity: BinanceCurrencyPairTick

        context("Mapping from db to entity") {
            mappedDomainEntity = mapper.mapFromDbToEntity(mappedDbEntity)

            it("should return correct value") {
                mappedDomainEntity.should.equal(fakeDataSource.getDomainEntity())
            }
        }
    }
})
