package com.sedsoftware.exchange.binance.parsing

import com.sedsoftware.exchange.binance.fakedata.FakeTickData
import com.sedsoftware.exchange.binance.readStringFromResource
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class TicksParsingTest : Spek({

    describe("Symbol tick parser") {

        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeTickData() }

        context("Parsing json") {
            val jsonText = readStringFromResource("/symbol_tick.json")
            val parsedEntity = fakeDataSource.getRawParsedEntity(jsonText)

            it("Should return correct entity") {
                parsedEntity.should.equal(fakeDataSource.getPredefinedParsedEntity())
            }
        }
    }
})
