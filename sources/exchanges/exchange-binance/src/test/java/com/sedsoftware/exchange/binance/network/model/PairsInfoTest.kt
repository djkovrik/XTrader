package com.sedsoftware.exchange.binance.network.model

import com.sedsoftware.core.test.blockingMemoized
import com.sedsoftware.core.test.get
import com.sedsoftware.core.utils.adapter.OffsetDateTimeAdapter
import com.sedsoftware.exchange.binance.Urls
import com.sedsoftware.exchange.binance.entity.BinanceCurrency
import com.sedsoftware.exchange.binance.mapper.BinanceSymbolsMapper
import com.winterbe.expekt.should
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

class PairsInfoTest : Spek({

    val adapter = OffsetDateTimeAdapter()
    val now: OffsetDateTime = Instant.now().atZone(ZoneOffset.UTC).toOffsetDateTime()
    val response: PairsInfo? by blockingMemoized { get(Urls.GET_CURRENCY_PAIRS, adapter, PairsInfo::class.java) }

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
            it("rateLimits should not be empty") {
                response?.rateLimits.should.not.be.empty
            }
            it("symbols should not be empty") {
                response?.symbols.should.not.be.empty
            }
        }

        context("Check server date mapping") {
            it("serverTime should not be null") {
                response?.serverTime.should.not.be.`null`
            }
            it("serverTime should have correct year") {
                response?.serverTime?.year.should.be.equal(now.year)
            }
            it("serverTime should have correct month") {
                response?.serverTime?.month.should.be.equal(now.month)
            }
            it("serverTime should have correct day") {
                response?.serverTime?.dayOfMonth.should.be.equal(now.dayOfMonth)
            }
            it("serverTime should have correct hour") {
                response?.serverTime?.hour.should.be.equal(now.hour)
            }
        }

        val baseSymbols by blockingMemoized {
            response?.symbols?.map { it.baseAsset }?.toSet() ?: emptySet()
        }
        val quoteSymbols by blockingMemoized {
            response?.symbols?.map { it.quoteAsset }?.toSet() ?: emptySet()
        }

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

        context("Check mapper") {
            val mapper = BinanceSymbolsMapper()

            it("Maps server time correctly") {
                response?.let { mapper.mapSyncInfoToDb(it).lastSyncDate.should.not.be.`null` }
            }

            it("Maps symbols correctly") {
                response?.let { pairsInfo ->
                    mapper.mapSymbolsToDb(pairsInfo).should.not.be.empty
                }
            }


            response?.let { pairsInfo ->
                val dbSymbols = mapper.mapSymbolsToDb(pairsInfo)

                dbSymbols.forEach { dbSymbol ->
                    it("Base db symbol ${dbSymbol.baseAsset} mapped to entity") {
                        BinanceCurrency.values().indexOf(mapper.mapDbToBaseCurrency(dbSymbol)).should.be.above(-1)
                    }

                    it("Quote db symbol ${dbSymbol.quoteAsset} mapped to entity") {
                        BinanceCurrency.values().indexOf(mapper.mapDbToMarketCurrency(dbSymbol)).should.be.above(-1)
                    }
                }
            }
        }
    }
})
