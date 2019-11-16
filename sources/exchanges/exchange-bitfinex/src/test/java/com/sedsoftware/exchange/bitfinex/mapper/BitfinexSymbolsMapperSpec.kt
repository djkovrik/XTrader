package com.sedsoftware.exchange.bitfinex.mapper

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.provider.CurrencyProvider
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class BitfinexSymbolsMapperSpec : Spek({

    describe("Check mapper") {

        val currencyProvider = object : CurrencyProvider {
            override suspend fun getCurrency(symbol: String): Currency =
                object : Currency {
                    override val name: String = "name"
                    override val label: String = "label"
                }
        }

        val mapper = BitfinexSymbolsMapper(currencyProvider)

        it("Splits six-letters symbol correctly") {
            val symbol = "DTXUST"
            val splitted = mapper.splitSymbol(symbol)

            splitted.first.should.be.equal("DTX")
            splitted.second.should.be.equal("UST")
        }

        it("Splits colon symbol correctly") {
            val symbol = "DUSK:BTC"
            val splitted = mapper.splitSymbol(symbol)

            splitted.first.should.be.equal("DUSK")
            splitted.second.should.be.equal("BTC")
        }

        it("Splits another colon symbol correctly") {
            val symbol = "BTC:USDT"
            val splitted = mapper.splitSymbol(symbol)

            splitted.first.should.be.equal("BTC")
            splitted.second.should.be.equal("USDT")
        }
    }
})
