package com.sedsoftware.exchange.binance.parsing

import com.sedsoftware.exchange.binance.fakedata.FakeSymbolsInfoData
import com.sedsoftware.exchange.binance.readStringFromResource
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class InfoParsingTest : Spek({

    describe("Symbol tick parser") {

        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeSymbolsInfoData() }

        context("Parsing json") {
            val jsonText = readStringFromResource("/symbols_info.json")
            val parsedDto = fakeDataSource.getRawParsedDto(jsonText)

            it("Should return correct entity") {
                parsedDto.should.equal(fakeDataSource.getPredefinedParsedDto())
            }
        }
    }
})
