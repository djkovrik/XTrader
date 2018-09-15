package com.sedsoftware.binance.parsing

import com.sedsoftware.binance.fakedata.FakeTickData
import com.sedsoftware.binance.readStringFromResource
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
