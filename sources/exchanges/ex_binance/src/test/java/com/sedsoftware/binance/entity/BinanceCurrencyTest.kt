package com.sedsoftware.binance.entity

import com.sedsoftware.binance.fakedata.FakeSymbolsInfoData
import com.sedsoftware.binance.readStringFromResource
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class BinanceCurrencyTest : Spek({

    describe("Full symbols list parsing") {

        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeSymbolsInfoData() }

        context("Parse existing currency pairs") {
            val jsonText = readStringFromResource("/symbols_info_full.json")
            val parsedDto = fakeDataSource.getRawParsedData(jsonText)

            parsedDto.symbols.forEach { symbol ->

                it("Base symbol ${symbol.baseAsset} should have defined value") {
                    enumValueOf<BinanceCurrency>(symbol.baseAsset).should.not.equal(null)
                }

                it("Quote symbol ${symbol.quoteAsset} should have defined value") {
                    enumValueOf<BinanceCurrency>(symbol.quoteAsset).should.not.equal(null)
                }
            }
        }
    }
})
