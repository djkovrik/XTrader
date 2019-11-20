package com.sedsoftware.exchange.bitfinex.mapper

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.provider.CurrencyProvider
import com.sedsoftware.core.test.blockingMemoized
import com.sedsoftware.core.test.getStringList
import com.sedsoftware.exchange.bitfinex.Urls
import com.winterbe.expekt.should
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class BitfinexSymbolsMapperSpec : Spek({

    val response: List<String>? by blockingMemoized { getStringList(Urls.GET_CURRENCY_PAIRS) }

    val currencyProvider = object : CurrencyProvider {
        override suspend fun getCurrency(symbol: String): Currency =
            object : Currency {
                override val name: String = "name"
                override val label: String = "label"
            }
    }

    val mapper = BitfinexSymbolsMapper(currencyProvider)

    describe("Check response") {
        it("Response should not be empty") {
            response.should.not.be.empty
        }
    }

    describe("Check mapper") {
        runBlocking {
            val mapped = mapper.mapSymbolsToDb(response!!)

            mapped.forEachIndexed { index, item ->
                it("Item ${response!![index]} should have symbol") {
                    item.symbol.should.not.be.empty
                }

                it("Item ${response!![index]} should have baseAsset") {
                    item.baseAsset.should.not.be.empty
                }

                it("Item ${response!![index]} should have quoteAsset") {
                    item.quoteAsset.should.not.be.empty
                }
            }
        }
    }

    describe("Check mapper utility functions") {

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
