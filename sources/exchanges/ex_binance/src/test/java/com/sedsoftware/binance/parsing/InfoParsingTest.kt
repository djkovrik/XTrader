package com.sedsoftware.binance.parsing

import com.sedsoftware.binance.fakedata.FakeInfoData
import com.sedsoftware.binance.readStringFromResource
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class InfoParsingTest : Spek({

    describe("Symbol tick parser") {

        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeInfoData() }

        context("Parsing json") {
            val jsonText = readStringFromResource("/symbols_info.json")
            val parsedDto = fakeDataSource.getRawParsedData(jsonText)

            it("Should return correct entity") {
                parsedDto.should.equal(fakeDataSource.getPredefinedParsedDto())
            }
        }
    }
})
