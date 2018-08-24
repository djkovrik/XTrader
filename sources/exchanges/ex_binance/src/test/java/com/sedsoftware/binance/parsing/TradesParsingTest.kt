package com.sedsoftware.binance.parsing

import com.sedsoftware.binance.fakedata.FakeTradesData
import com.sedsoftware.binance.mapper.BinanceTradesMapper
import com.sedsoftware.binance.network.model.SymbolTradeModel
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class TradesParsingTest : Spek({

    describe("Symbol trades parser") {

        val mapper by memoized { BinanceTradesMapper() }
        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeTradesData() }

        lateinit var parsedList: List<SymbolTradeModel>

        context("Parsing json") {
            parsedList = fakeDataSource.getRawParsedData()

            it("Should return correct list") {
                parsedList.should.equal(fakeDataSource.getPredefinedParsedList())
            }
        }
    }
})