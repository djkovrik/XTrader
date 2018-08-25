package com.sedsoftware.binance.parsing

import com.sedsoftware.binance.fakedata.FakeDepthsData
import com.sedsoftware.binance.readStringFromResource
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class DepthsParsingTest : Spek({

    describe("Symbol depths parser") {

        val fakeDataSource by memoized(CachingMode.SCOPE) { FakeDepthsData() }

        context("Parsing json") {
            val jsonText = readStringFromResource("/symbol_depths.json")
            val parsedEntity = fakeDataSource.getRawParsedData(jsonText)

            it("Should return correct entity") {
                parsedEntity.should.equal(fakeDataSource.getPredefinedParsedEntity())
            }
        }
    }
})
