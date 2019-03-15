package com.sedsoftware.exchange.binance.network.model.dto

import com.sedsoftware.core.tests.blockingMemoized
import com.sedsoftware.core.tests.get
import com.sedsoftware.exchange.binance.Urls
import com.sedsoftware.exchange.binance.entity.BinanceCurrency
import com.sedsoftware.exchange.binance.network.model.PairsInfo
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class PairsInfoTest : Spek({

    val response: PairsInfo? by blockingMemoized { get(Urls.CURRENCY_PAIRS, PairsInfo::class.java) }

    describe("Binance exchangeInfo response test") {

        context("Check response state") {
            it("Response should not be null") {
                response.should.not.be.`null`
            }
        }

        context("Check response fields") {
            it("timezone should not be empty") {
                response?.timezone.should.not.be.empty
            }
            it("serverTime should be above zero") {
                response?.serverTime.should.be.above(0)
            }
            it("rateLimits should not be empty") {
                response?.rateLimits.should.not.be.empty
            }
            it("symbols should not be empty") {
                response?.symbols.should.not.be.empty
            }
        }

        val baseSymbols by blockingMemoized { response?.symbols?.map { it.baseAsset }?.toSet() ?: emptySet() }
        val quoteSymbols by blockingMemoized { response?.symbols?.map { it.quoteAsset }?.toSet() ?: emptySet() }

        context("Check that all symbols defined") {

            baseSymbols.forEach { symbol ->
                it("Base symbol $symbol should have defined value") {
                    enumValueOf<BinanceCurrency>(symbol).should.not.be.`null`
                }
            }

            quoteSymbols.forEach { symbol ->
                it("Quote symbol $symbol should have defined value") {
                    enumValueOf<BinanceCurrency>(symbol).should.not.be.`null`
                }
            }
        }

        context("Check that no currencies removed") {
            BinanceCurrency.values().forEach { symbol ->
                it("Currency ${symbol.name} from BinanceCurrency still valid") {
                    (baseSymbols.contains(symbol.name) || quoteSymbols.contains(symbol.name)).should.be.`true`
                }
            }
        }
    }
})
